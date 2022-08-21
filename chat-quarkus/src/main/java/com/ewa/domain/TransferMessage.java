package com.ewa.domain;

import lombok.Value;

import java.io.Serializable;

@Value
public class TransferMessage implements Serializable {
    private final String clientName;
    private final String textMsg;
    private final int roomNr;
    private final boolean publish;

}
