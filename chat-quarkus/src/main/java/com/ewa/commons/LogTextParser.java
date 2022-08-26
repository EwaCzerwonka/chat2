package com.ewa.commons;

import com.ewa.persistence.ChatEntity;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class LogTextParser {
    private static final String HISTORY_PREFIX = "History log for room {0}: \n { \n";
    private static final String HISTORY_SUFFIX = "\n}";

    public String prepareMsgHistory(List<ChatEntity> chatList, int roomNr){
        return chatList.stream()
                .map(chatEntity -> chatEntity.getClientName() + " --> " + chatEntity.getMessage())
                .collect(Collectors.joining("\n", HISTORY_PREFIX.formatted(roomNr), HISTORY_SUFFIX));
    }

}
