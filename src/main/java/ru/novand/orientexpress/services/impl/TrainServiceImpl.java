package ru.novand.orientexpress.services.impl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.orientexpress.domain.entities.*;
import ru.novand.orientexpress.services.interfaces.TrainService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class TrainServiceImpl implements TrainService {

    private final TrainDAO trainDAO;
    private final static String QUEUE_NAME = "hello";
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    public TrainServiceImpl(TrainDAO trainDAO) {
        this.trainDAO = trainDAO;
    }

    @Override
    public Train addTrain(String trainname,String traincode, int seatsnumber) {
        logger.debug("addTrain method was called");
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule());
        Train train = new Train(trainname,traincode,seatsnumber);
        return trainDAO.save(train);
    }

    @Override
    public List<Train> getAllTrains() {
        logger.debug("getAllTrains method was called");
        List<Train> list = trainDAO.findAll();
        return list;
    }

    @Override
    public void sendMessage(int id) {

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