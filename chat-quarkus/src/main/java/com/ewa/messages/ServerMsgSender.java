package com.ewa.messages;

import com.ewa.commons.CommonNames;
import com.ewa.domain.TransferMessage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;


@ApplicationScoped
public class ServerMsgSender {

    @Inject
    ConnectionFactory connectionFactory;

    public void sendMessage(TransferMessage message) {
        JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE);
        JMSProducer messageProducer = context.createProducer();
        Topic topic = context.createTopic(CommonNames.TOPIC);
        messageProducer.send(topic, message);
    }
}
