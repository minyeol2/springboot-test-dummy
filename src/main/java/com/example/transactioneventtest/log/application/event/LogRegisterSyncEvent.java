package com.example.transactioneventtest.log.application.event;

import com.example.transactioneventtest.log.dto.LogDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LogRegisterSyncEvent {
    private LogDTO logDTO;

    @Builder
    public LogRegisterSyncEvent(LogDTO logDTO) {
        this.logDTO = logDTO;
    }
}
