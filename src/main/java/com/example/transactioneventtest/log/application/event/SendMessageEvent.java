package com.example.transactioneventtest.log.application.event;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SendMessageEvent {
    private String message;

    @Builder
    public SendMessageEvent(String message) {
        this.message = message;
    }
}
