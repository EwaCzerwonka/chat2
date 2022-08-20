package com.ewa.messages;

import com.ewa.commons.CommonNames;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;

/**
 * A bean producing random prices every 5 seconds and sending them to the prices JMS queue.
 */

@Log
@ApplicationScoped
public class ServerMsgSender {

    @Inject
    ConnectionFactory connectionFactory;

    public void sendMessage(String message) {
        log.info(" -> MessageProducer.sendMessage message = %s".formatted(message));

        JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE);
        JMSProducer messageProducer = context.createProducer();
        Topic topic = context.createTopic(CommonNames.TOPIC);

        messageProducer.send(topic, message);
    }
}
