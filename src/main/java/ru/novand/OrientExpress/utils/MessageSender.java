package ru.novand.orientexpress.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.novand.orientexpress.services.impl.BookServiceImpl;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MessageSender {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    private final static String QUEUE_NAME = "hello";

    public static void sendMessage(int id) {

        logger.debug("sendMessage method was called");
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            logger.info(" [x] Sent '" + message + "'");

            channel.close();
            connection.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
        catch (TimeoutException e){
            System.out.println(e);
        }


    }

}
