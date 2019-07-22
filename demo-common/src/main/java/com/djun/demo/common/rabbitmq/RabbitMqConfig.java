package com.djun.demo.common.rabbitmq;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by DJun on 2019/7/22.
 * desc: 消息队列配置
 */
@Configuration
public class RabbitMqConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String EXCHANGE_A = "my-mq-exchange_A";
    public static final String QUEUE_A = "QUEUE_A";
    public static final String ROUTING_KEY_A = "spring-boot-routingKey_A";

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private int port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;

    /**
     * 连接Rabbit
     * @return 连接对象
     */
    @Bean
     ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    /**
     * 创建消息模板
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    /*
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     *    FanoutExchange  : 将消息分发到所有的绑定队列，无routing_key的概念
     *    HeadersExchange ：通过添加属性key-value匹配
     *    DirectExchange  : 按照routing_key分发到指定队列
     *    TopicExchange   :  多关键字匹配
     */

    /**
     * 创建交换器
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE_A);
    }

    /**
     * 创建队列A
     */
    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_A, true); //队列持久
    }

    /**
     * 使用路由键（routingKey）把队列（Queue）绑定到交换器（Exchange）
     */
    @Bean
    public Binding bindingA() {
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(RabbitMqConfig.ROUTING_KEY_A);
    }

}
