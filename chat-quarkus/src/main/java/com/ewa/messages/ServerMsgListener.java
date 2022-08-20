package com.ewa.messages;


import com.ewa.commons.CommonNames;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A bean consuming prices from the JMS queue.
 */
@Log
@ApplicationScoped
public class ServerMsgListener implements Runnable {
    //private static final String JMS_TOPIC = "chat";
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
        String textMsg;
        try(JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE)){
            JMSConsumer consumer = context.createConsumer(context.createTopic(CommonNames.TOPIC));
            while(true){
                Message message = consumer.receive();
                if(message == null) return;
                textMsg = message.getBody(String.class);
                log.info(" -> PriceConsumer received message = %s".formatted(textMsg));

            }
        } catch (JMSException e){
            throw new RuntimeException(e);
        }
    }
}
