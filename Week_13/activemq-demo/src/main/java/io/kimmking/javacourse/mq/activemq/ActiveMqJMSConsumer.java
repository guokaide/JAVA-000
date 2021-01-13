package io.kimmking.javacourse.mq.activemq;

// ActiveMQ JMS Provider(Library that implements the JMS API)
// Apache Qpid JMS Library: AMQP
import org.apache.qpid.jms.JmsConnectionFactory;

import javax.jms.*;

public class ActiveMqJMSConsumer {
    public static void main(String[] args) throws JMSException {
//        consume("QUEUE", "test.queue");
        consume("TOPIC", "test.topic");
    }

    private static void consume(String type, String name) throws JMSException {
        //https://activemq.apache.org/amqp
        JmsConnectionFactory factory = new JmsConnectionFactory("amqp://localhost:5672");
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination;
        if (type.equals("QUEUE")) {
            destination = session.createQueue(name);
        } else {
            destination = session.createTopic(name);
        }

        MessageConsumer consumer = session.createConsumer(destination);

        while (true) {
            Message message = consumer.receive();
            System.out.println("Received = " + ((TextMessage) message).getText());
        }
    }

}
