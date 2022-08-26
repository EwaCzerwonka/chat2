package com.ewa.logger;

import com.ewa.ChatClient;
import com.ewa.domain.ChatRoom;
import com.ewa.domain.ServerEventType;
import com.ewa.domain.TransferMessage;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Log
public class MessageLogger {

    @Inject
    ChatRoom chatRoom;

    public void log(TransferMessage transferMessage){
        if((ServerEventType.PUBLIC.equals(transferMessage.getType())//transferMessage.isPublish()
                || ChatClient.clientName.equals(transferMessage.getClientName()))
                && chatRoom.getRoomNumber() == transferMessage.getRoomNr()) {
            log.info(transferMessage.getTextMsg());
        }
    }
}
