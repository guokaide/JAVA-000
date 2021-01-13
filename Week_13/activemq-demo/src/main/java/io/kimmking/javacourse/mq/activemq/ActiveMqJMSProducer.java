package io.kimmking.javacourse.mq.activemq;

// ActiveMQ JMS Provider(Library that implements the JMS API)
// Apache Qpid JMS Library: AMQP
import org.apache.qpid.jms.JmsConnectionFactory;

import javax.jms.*;

// JMS: https://javaee.github.io/tutorial/toc.html
// https://activemq.apache.org/components/artemis/documentation/latest/using-jms.html
public class ActiveMqJMSProducer {
    public static void main(String[] args) throws JMSException {
        produce("QUEUE", "test.queue");
        produce("TOPIC", "test.topic");
    }

    private static void produce(String type, String name) throws JMSException {
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

        MessageProducer producer = session.createProducer(destination);

        int i = 0;
        while (i++ < 100) {
            TextMessage message = session.createTextMessage("message " + i);
            producer.send(message);
        }
        connection.close();
    }

}
