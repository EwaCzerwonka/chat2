package com.ewa.messages;


import com.ewa.domain.TransferMessage;
import com.ewa.persistence.ChatRepository;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Log
@ApplicationScoped
public class MessageService {

    @Inject
    ServerWorker worker;
    @Inject
    ChatRepository chatRepository;

    public void processTransferMessage(TransferMessage transferMessage){
        if(transferMessage.isPublish()) {
            chatRepository.createChatEntry(transferMessage);
        }
    }

}
