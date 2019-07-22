# SpringCloud 的脚手架
目的是帮助我们快速搭建分布式的架子
## 一、关于引用的技术
### 1、Spring Cloud
Spring Boot、Redis、Eureka、Hystrix、feign、turbine、Zuul
如果你需要使用Config的话，只需要创建一个Config的Server端即可。
### 2、Spring Boot
lombok、Swagger2、druid

## 二、关于文件模块的说明
模块后面的数字为端口号<br/>
spring-cloud-scaffold <br/>
|<br/>
+——common —— 公用模块<br/>
|<br/>
+——consumer —— 消费者模块<br/>
|<br/>
+——eureka —— eureka服务端（其它两个是集群已配置好了，集群连接一下即可使用）<br/>
|<br/>
+——provider —— 生产接口的微服务 <br/>
|<br/>
+——turbine —— 微服务多了再启用吧，暂时用不上<br/>
|<br/>
+——Zuul —— 路由网关服务端<br/>
|<br/>
+—— mgb —— mybatis generator插件自动生成model、mapper、xml