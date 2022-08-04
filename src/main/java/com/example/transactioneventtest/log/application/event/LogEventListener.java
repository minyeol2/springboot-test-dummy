package com.example.transactioneventtest.log.application.event;

import com.example.transactioneventtest.log.application.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;
import static org.springframework.transaction.event.TransactionPhase.BEFORE_COMMIT;

@Slf4j
@Component
@RequiredArgsConstructor
public class LogEventListener {

    private final LogService logService;

    @EventListener
    public void registerInboundAdjustEvent(final LogRegisterSyncEvent event) {
        logService.registerLogSync_1(event.getLogDTO());
    }

    @Async
    @EventListener
    public void registerInboundAdjustEvent(final LogRegisterAsyncEvent event) {
        logService.registerLogAsync(event.getReferencdId());
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void registerInboundAdjustEvent(final LogRegisterSyncTranEvent event) {
        logService.registerLogSync_2(event.getReferencdId());
    }

    @Async
    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void registerInboundAdjustEvent(final LogRegisterAsyncTranEvent event) {
        logService.registerLogAsync(event.getReferencdId());
    }

    @Async
    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void registerInboundAdjustEvent(final SendMessageEvent event) {
        logService.sendMessage(event.getMessage());
    }

    @Async
    @TransactionalEventListener(phase = BEFORE_COMMIT)
    public void registerLogBeforeCommitEvent(final LogRegisterBeforeCommitEvent event) {
        logService.registerLogBeforeCommit();
    }

}
