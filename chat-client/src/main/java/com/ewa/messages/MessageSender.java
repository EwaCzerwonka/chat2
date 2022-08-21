package com.ewa.messages;

import com.ewa.commons.CommonNames;
import com.ewa.domain.TransferMessage;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;

@Log
@ApplicationScoped

public class MessageSender {

    @Inject
    ConnectionFactory connectionFactory;

    public void sendMessage(TransferMessage message) {
        log.info(" -> MessageProducer.sendMessage message = %s".formatted(message));

        JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE);
        JMSProducer messageProducer = context.createProducer();
        Topic topic = context.createTopic(CommonNames.TOPIC);

        messageProducer.send(topic, message);
    }
}
