package com.djun.demo.common.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * created by DJun on 2019/7/22 12:22
 * desc:
 */
@Component
public class MsgProducer implements RabbitTemplate.ConfirmCallback {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
        String context = "Hello";
        logger.info("发送时间："+ new Date());
        amqpTemplate.convertAndSend(RabbitMqConfig.ROUTING_KEY_A, context);
    }

    /**
     * 回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info(" 回调id:" + correlationData);
        if (ack) {
            logger.info("消息成功发送");
        } else {
            logger.info("消息消费失败:" + cause);
        }
    }
}