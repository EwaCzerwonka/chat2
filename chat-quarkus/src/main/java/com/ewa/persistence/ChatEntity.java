package com.ewa.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class ChatEntity {
    @GeneratedValue
    @Id
    private Long id;
    private String clientName;
    private Integer roomNumber;
    private String message;


}
