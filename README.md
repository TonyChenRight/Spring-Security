# Spring-Security 深入学习

## 主要学习技术
>* spring security
>* spring social
>* spring security oauth2

## 项目结构
>* doc : 存放使用说明及初始化语句
>* huobi-serurity : 主模块
>* huobi-serurity-core : 核心业务逻辑
>* huobi-serurity-browser : 浏览器安全特定代码
>* huobi-serurity-app : app相关特定代码
>* huobi-serurity-authorize : Rbac权限相关服务
>* huobi-serurity-demo : 样例程序
>* test 作为第三方集成服务使用

## 基本技术点  tag v1.0 
>* restful api编写及常用注解
>* jsonPath语法限定path参数
>* @JsonView注解控制返回视图
>* @valid、hibernate-validator常用校验注解及使用自定义注解
>* filter过滤器、interceptor拦截器、aspect切面的使用及配置
>* 异步处理：Callable方式、DeferredResult方式
>* swagger-ui 2生成api文档（最新使用swagger-bootstrap-ui）
>* WireMock 伪造api接口

## spring security相关 tag v1.1
>* 基于用户名和密码的表单登录
>* 自定义成功和失败处理器
>* 记住我功能实现
>* 基于短信验证码的表单登录

## spring social相关
>* OAuth2.0协议支持
>* QQ登录实现
>* 微信登录实现
>* 单机及集群下session管理

## spring security oauth相关
>* Spring Security OAuth相关重构
>* JWT token令牌配置

## spring security 授权相关
>* spring security授权方法
>* Spring 表达式

## 项目使用  tag v2.0
>* 1.引入依赖(pom.xml)
``` 
    <dependency>
        <groupId>com.huobi.security</groupId>
        <artifactId>huobi-security-browser</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
```

>* 2.配置系统(参见 application-example.properties)

>* 3.增加UserDetailsService接口实现

>* 4.如果需要记住我功能，需要创建数据库表(参见 db.sql)

>* 5.如果需要社交登录功能，需要以下额外的步骤
``` 
    1).配置appId和appSecret
    2).创建并配置用户注册页面，并实现注册服务(需要配置访问权限)，注意在服务中要调用ProviderSignInUtils的doPostSignUp方法。
    3).添加SocialUserDetailsService接口实现
    4).创建社交登录用的表 (参见 db.sql)
```

>* 6.登录用户名为admin,密码123456

