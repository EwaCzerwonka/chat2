package com.ewa.persistence;

import com.ewa.domain.TransferMessage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class ChatRepository {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void createChatEntry(TransferMessage transferMessage){
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setClientName(transferMessage.getClientName());
        chatEntity.setRoomNumber(transferMessage.getRoomNr());
        chatEntity.setMessage(transferMessage.getTextMsg());
        entityManager.persist(chatEntity);
    }
}
