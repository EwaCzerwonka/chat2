package com.ewa.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@NamedQuery(name = ChatEntity.GET_HISTORY, query = "select c from ChatEntity c where c.roomNumber = :roomNumber order by c.id asc")
@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class ChatEntity {
    public static final String GET_HISTORY = "chatHistory";

    @GeneratedValue
    @Id
    private Long id;
    private String clientName;
    private Integer roomNumber;
    private String message;

}
