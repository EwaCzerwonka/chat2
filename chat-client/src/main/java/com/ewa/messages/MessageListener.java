package com.ewa.messages;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log
@ApplicationScoped
public class MessageListener implements Runnable{
    private static final String JMS_TOPIC = "chat";

    @Inject
    ConnectionFactory connectionFactory;

    private final ExecutorService scheduler = Executors.newSingleThreadExecutor();


    void onStart(@Observes StartupEvent ev){
        scheduler.submit(this);
    }

    void onStop(@Observes ShutdownEvent ev){
        scheduler.shutdown();
    }

    @Override
    public void run() {
        String text;
        try(JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE)){
            Topic topic = context.createTopic(JMS_TOPIC);
            JMSConsumer consumer = context.createConsumer(topic);
            while(true){
                Message message = consumer.receive();
                if(message == null) return;
                text = message.getBody(String.class);
                log.info("klient przeczytano: " + text);
            }
        } catch (JMSException e){
            throw new RuntimeException(e);
        }
    }
}
