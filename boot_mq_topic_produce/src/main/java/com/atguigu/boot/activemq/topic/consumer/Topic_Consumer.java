package com.atguigu.boot.activemq.topic.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * @author lixiang
 * @date 2020-03-26-11:39
 */
@Component
public class Topic_Consumer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Topic topic;

    @JmsListener(destination = "${myTopic}")
    public void consumerTopic(TextMessage textMessage) throws JMSException {
        System.out.println("********消息订阅者1号 收到订阅:" + textMessage.getText());
    }

    @JmsListener(destination = "${myTopic}")
    public void consumerTopic2(TextMessage textMessage) throws JMSException {
        System.out.println("********消息订阅者2号 收到订阅:" + textMessage.getText());
    }
}
