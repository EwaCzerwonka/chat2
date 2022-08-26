package com.ewa.persistence;

import com.ewa.commons.LogTextParser;
import com.ewa.domain.TransferMessage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class ChatRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    LogTextParser logTextParser;

    @Transactional
    public void createChatEntry(TransferMessage transferMessage){
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setClientName(transferMessage.getClientName());
        chatEntity.setRoomNumber(transferMessage.getRoomNr());
        chatEntity.setMessage(transferMessage.getTextMsg());
        entityManager.persist(chatEntity);
    }

    @Transactional
    public String getUserHistory(TransferMessage transferMessage){
        var query = entityManager.createNamedQuery(ChatEntity.GET_HISTORY, ChatEntity.class);
        query.setParameter("roomNumber", transferMessage.getRoomNr());
        var result = query.getResultList();
        if(result.isEmpty()){
            return null;
        } else {
            return logTextParser.prepareMsgHistory(result, transferMessage.getRoomNr());
        }
    }
}
