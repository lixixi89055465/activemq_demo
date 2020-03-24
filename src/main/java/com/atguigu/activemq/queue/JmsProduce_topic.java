package com.atguigu.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce_topic {
    public static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    public static final String TOPIC_NAME = "topic-atguigu";

    public static void main(String[] args) throws JMSException {
        //1.创建链接工厂,按照给定的url地址，采用默认的用户名和秘密
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2.通过连接工厂，获得连接的connection并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3.创建会话session
        //两个参数，第一个叫事务，第二个叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地（具体是队列还是主题 topic)
        //Collection collection=new ArrayList();
//        Queue queue = session.createQueue(TOPIC_NAME);
        Topic topic = session.createTopic(TOPIC_NAME);
        //5. 创建消息的生产者
        MessageProducer messageProducer = session.createProducer(topic);
        //6. 通过使用messageProducer成产3条消息发送到MQ的队列里面
        for (int i = 0; i < 3; i++) {
            //7. 创建消息，好比学生按照阳哥的要修写好的面试题消息 。
            TextMessage textMessage = session.createTextMessage("Topic _ name---" + i);//理解为一个字符串
            //8. 通过messageProducer发送给mq
            messageProducer.send(textMessage);
        }
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("********TOPIC_NAME 消息发送到MQ完成");

    }
}
