package com.ewa.messages;

import com.ewa.domain.TransferMessage;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@RequiredArgsConstructor
public class ServerWorker {
    @Inject
    ServerMsgSender messageSender;

    public void sendMessage(TransferMessage transferMessage){
        messageSender.sendMessage(transferMessage);
    }
}
