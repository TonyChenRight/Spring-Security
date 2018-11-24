package com.huobi.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;


    /**
     * spring context 初始化完毕调用事件
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(() -> {
            while(true){
                //模拟监听 消息队列消息，并设置 result结果
                if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())){
                    String orderNumber = mockQueue.getCompleteOrder();
                    logger.info("返回订单处理结果："+orderNumber);
                    deferredResultHolder.getMap().get(orderNumber).setResult("place order success");

                    mockQueue.setCompleteOrder(null);
                }else{
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
