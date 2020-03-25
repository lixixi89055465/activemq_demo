package com.atguigu.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumer_TX {
    public static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("我是1号消费者 。。。。");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //自动签收，第一个事务，第二个自动签收
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //第一个事务，若是true,则必须加提交 ；第二个手动签收,必须加acknowledge;
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        Queue queue = session.createQueue(QUEUE_NAME);
        //创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        while (true) {
            TextMessage message = (TextMessage) consumer.receive(4000L);
            if (null != message) {
                System.out.println("*****消费者接收到消息 ***" + message.getText());
                //显式签收 
                message.acknowledge();
            } else {
                break;
            }
        }
        consumer.close();
        session.commit();
        session.close();
        connection.close();
    }
}
