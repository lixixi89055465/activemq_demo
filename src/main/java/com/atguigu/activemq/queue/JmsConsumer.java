package com.atguigu.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumer {
    //    public static final String ACTIVEMQ_URL = "tcp://localhost:61616";
//    public static final String ACTIVEMQ_URL = "nio://localhost:61618";
    public static final String ACTIVEMQ_URL = "nio://localhost:61608";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("我是1号消费者 。。。。");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        //创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
//        while (true) {
//            TextMessage message = (TextMessage) consumer.receive(4000L);
//            if (null != message) {
//                System.out.println("*****消费者接收到消息 ***" + message.getText());
//            } else {
//                break;
//            }
//        }
//        consumer.close();
//        session.close();
//        connection.close();
        //消息监听
        consumer.setMessageListener(new MessageListener() {

            @Override
            public void onMessage(Message message) {
                if (null != message && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("*****消费者接收到消息 ：" + textMessage.getText());
                        System.out.println("*****消费者接收到消息属性 ：" + textMessage.getStringProperty("c01"));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
                if (null != message && message instanceof MapMessage) {
                    MapMessage mapMessage = (MapMessage) message;
                    try {
                        System.out.println("*****消费者接收到消息 ：" + mapMessage.getString("k1"));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
        System.in.read();
        consumer.close();
        session.close();
    }
}
