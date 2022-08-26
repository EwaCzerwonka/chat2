package com.ewa.messages;


import com.ewa.domain.ServerEventType;
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
        switch(transferMessage.getType()){
            case PUBLIC -> chatRepository.createChatEntry(transferMessage);
            case HISTORY_READ -> sendHistory(transferMessage);
            }
        }

        private void sendHistory(TransferMessage transferMessage){
            String msg = chatRepository.getUserHistory(transferMessage);
            if (msg != null) {
                var historyMsg = new TransferMessage(transferMessage.getClientName(), msg,
                        transferMessage.getRoomNr(), ServerEventType.INTERNAL);
                worker.sendMessage(historyMsg);
            }
        }

}
