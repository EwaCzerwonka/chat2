package com.ewa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Getter
@Setter
public class ChatRoom {
    private Integer roomNumber = 0;
}
