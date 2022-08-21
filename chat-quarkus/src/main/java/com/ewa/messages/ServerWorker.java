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

    public void sendMessage(String msg){
        TransferMessage transferMessage = process(msg);
        if (transferMessage != null){
            messageSender.sendMessage(transferMessage);
        }
    }

    private TransferMessage process(String msg) {
        TransferMessage transferMessage = new TransferMessage("", msg, 0, false);

        return transferMessage;
    }

}
