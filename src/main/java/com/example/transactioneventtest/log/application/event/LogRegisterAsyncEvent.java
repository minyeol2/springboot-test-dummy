package com.example.transactioneventtest.log.application.event;

import com.example.transactioneventtest.log.dto.LogDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LogRegisterAsyncEvent {
    private Long referencdId;

    @Builder
    public LogRegisterAsyncEvent(Long referencdId) {
        this.referencdId = referencdId;
    }
}
