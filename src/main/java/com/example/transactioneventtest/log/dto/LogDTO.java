package com.example.transactioneventtest.log.dto;

import com.example.transactioneventtest.log.domain.Log;
import com.example.transactioneventtest.order.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class LogDTO {

    private Long id;

    private Long referenceId;

    private String tableName;

    private String logMessage;

    @Builder
    public LogDTO(Long id, Long referenceId, String tableName, String logMessage) {
        this.id = id;
        this.referenceId = referenceId;
        this.tableName = tableName;
        this.logMessage = logMessage;
    }

    public Log toEntity() {
        return Log.builder()
                    .referenceId(this.referenceId)
                    .tableName(this.tableName)
                    .logMessage(this.logMessage)
                    .build();
    }

}
