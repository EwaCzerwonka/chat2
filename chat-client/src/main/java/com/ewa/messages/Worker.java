package com.ewa.messages;

import com.ewa.ChatClient;
import com.ewa.domain.TransferMessage;
import com.ewa.messages.MessageSender;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@RequiredArgsConstructor
public class Worker {
    @Inject
    MessageSender messageSender;

    public void sendMessage(String msg){
        TransferMessage transferMessage = process(msg);
        messageSender.sendMessage(transferMessage);
    }

    private TransferMessage process(String msg) {
        int roomNr = 0;
        return new TransferMessage(ChatClient.clientName, msg, roomNr);
    }
}
