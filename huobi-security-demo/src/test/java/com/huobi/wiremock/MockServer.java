package com.huobi.wiremock;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockServer {

    @Test
    public void testDeploy() throws IOException {
        configureFor(8062);
        removeAllMappings();    //清空配置

        mock("/order/1","01");
        mock("/order/2","02");
    }

    private void mock(String url, String file) throws IOException {
        ClassPathResource resource =new ClassPathResource("mock/response/"+file+".json");
        String content= FileUtils.readFileToString(resource.getFile(),"UTF-8");
        stubFor(get(urlPathEqualTo(url))
                .willReturn(aResponse().withBody(content)
                        .withStatus(200)));
    }
}
