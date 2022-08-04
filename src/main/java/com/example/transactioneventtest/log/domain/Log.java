package com.example.transactioneventtest.log.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "LOG")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "reference_id")
    private Long referenceId;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "log_message")
    private String logMessage;

    @Builder
    public Log(Long id, Long referenceId, String tableName, String logMessage) {
        this.id = id;
        this.referenceId = referenceId;
        this.tableName = tableName;
        this.logMessage = logMessage;
    }

}
