package com.ewa.messages;


import com.ewa.domain.TransferMessage;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Log
@ApplicationScoped
public class MessageService {

    @Inject
    ServerWorker worker;

    public void processTransferMessage(TransferMessage transferMessage){
        if(transferMessage.isPublish()) {
            worker.sendMessage("Resending message " + transferMessage.getTextMsg());
        }
    }

}
