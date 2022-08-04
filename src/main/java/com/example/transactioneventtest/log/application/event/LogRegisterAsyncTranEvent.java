package com.example.transactioneventtest.log.application.event;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LogRegisterAsyncTranEvent {
    private Long referencdId;

    @Builder
    public LogRegisterAsyncTranEvent(Long referencdId) {
        this.referencdId = referencdId;
    }
}
