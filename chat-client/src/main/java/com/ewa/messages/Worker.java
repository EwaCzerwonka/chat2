package com.ewa.messages;

import com.ewa.ChatClient;
import com.ewa.commons.OptionTypes;
import com.ewa.commons.TextParser;
import com.ewa.domain.ChatRoom;
import com.ewa.domain.TransferMessage;
import io.quarkus.runtime.Quarkus;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@RequiredArgsConstructor
public class Worker {
    @Inject
    MessageSender messageSender;

    @Inject
    ChatRoom chatRoom;

    public void sendMessage(String msg){
        TransferMessage transferMessage = process(msg);
        if (transferMessage != null){
            messageSender.sendMessage(transferMessage);
        }
    }

    private TransferMessage process(String msg) {
        TransferMessage transferMessage;
        if (msg.endsWith(OptionTypes.QUIT.label)) {
            transferMessage = exit(msg, OptionTypes.QUIT.label);
        } else if (msg.contains(OptionTypes.JOIN.label)) {
            transferMessage = joinRoom(msg, OptionTypes.JOIN.label);
        } else if (msg.endsWith(OptionTypes.HELP.label)) {
            transferMessage = new TransferMessage(ChatClient.clientName, OptionTypes.MENU.label, chatRoom.getRoomNumber(), false);
        }
        /*else if(msg.contains(OptionTypes.HISTORY.label)) {
            readHistory(msg, OptionTypes.HISTORY.label);
        } */
        else {
            transferMessage = new TransferMessage(ChatClient.clientName, msg, chatRoom.getRoomNumber(), true);
        }
        return transferMessage;
    }

    private TransferMessage exit(String text, String separator) {
        Integer roomNumber = chatRoom.getRoomNumber();
        if (roomNumber != 0) {
            String leaveText = TextParser.getFirstPart(text, separator) + " Exited from room: " + roomNumber;
            chatRoom.setRoomNumber(0);
            return new TransferMessage(ChatClient.clientName, leaveText, roomNumber, true);
        } else {
            Quarkus.asyncExit();
        }
        return null;
    }

    private TransferMessage joinRoom(String text, String separator) {
        Integer number = TextParser.parseLastNumber(text, separator);
        if (number != 0) {
            chatRoom.setRoomNumber(number);
            String msg = String.format("%s Joined room %s", ChatClient.clientName, number);
            return new TransferMessage(ChatClient.clientName, msg, number, true);
        }
        return null;
    }
}
