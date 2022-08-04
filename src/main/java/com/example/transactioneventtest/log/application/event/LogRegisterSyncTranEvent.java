package com.example.transactioneventtest.log.application.event;

import com.example.transactioneventtest.log.dto.LogDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LogRegisterSyncTranEvent {
    private Long referencdId;

    @Builder
    public LogRegisterSyncTranEvent(Long referencdId) {
        this.referencdId = referencdId;
    }
}
