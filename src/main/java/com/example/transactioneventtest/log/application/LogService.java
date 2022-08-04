package com.example.transactioneventtest.log.application;

import com.example.transactioneventtest.log.domain.Log;
import com.example.transactioneventtest.log.dto.LogDTO;
import com.example.transactioneventtest.log.repository.LogRepository;
import com.example.transactioneventtest.order.domain.Order;
import com.example.transactioneventtest.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.example.transactioneventtest.SpringbootTestDummyApplication.throwNPE;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    private final OrderRepository orderRepository;


    @Transactional
    public void registerLogSync_1(LogDTO logDTO) {
        Thread mainThread = Thread.currentThread();
        log.info("[ThreadID={}]",mainThread.getId() + " : Log서비스 로그저장 메서드 실행");

        Log logs = logDTO.toEntity();
        logRepository.save(logs);
        //throwNPE("LogService에서 강제발생");
        log.info("[ThreadID={}]",mainThread.getId() + " : Log서비스 로그저장 메서드 종료");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registerLogSync_2(Long referencdId) {
        Thread mainThread = Thread.currentThread();
        log.info("[ThreadID={}]",mainThread.getId() + " : Log서비스 로그저장 메서드 실행");
        Order order = orderRepository.findById(referencdId).orElseThrow();

        String logMessage = String.format("주문상품 : [%d], 주문수량 : [%d],  비고내용 : [%s]",order.getOrderItemId(), order.getOrderQuantity(), order.getRemark());
        Log logs = LogDTO.builder().referenceId(order.getId()).tableName("ORDERS").logMessage(logMessage).build().toEntity();
        logRepository.save(logs);
        //throwNPE("LogService에서 강제발생");
        log.info("[ThreadID={}]",mainThread.getId() + " : Log서비스 로그저장 메서드 종료");
    }

    @Transactional
    public void registerLogAsync(Long referencdId) {
        Thread mainThread = Thread.currentThread();
        log.info("[ThreadID={}]",mainThread.getId() + " : Log서비스 로그저장 메서드 실행");
        Order order = orderRepository.findById(referencdId).orElseThrow();

        String logMessage = String.format("주문상품 : [%d], 주문수량 : [%d],  비고내용 : [%s]",order.getOrderItemId(), order.getOrderQuantity(), order.getRemark());
        Log logs = LogDTO.builder().referenceId(order.getId()).tableName("ORDERS").logMessage(logMessage).build().toEntity();
        logRepository.save(logs);
        //throwNPE("LogService에서 강제발생");
        log.info("[ThreadID={}]",mainThread.getId() + " : Log서비스 로그저장 메서드 종료");
    }

    @Transactional
    public void sendMessage(String message) {
        Thread mainThread = Thread.currentThread();
        log.info("[ThreadID={}] : Send message 실행  [message : {}]" ,mainThread.getId(), message);
    }

    @Transactional
    public void registerLogBeforeCommit() {
        Thread mainThread = Thread.currentThread();
        log.info("[ThreadID={}] : Log서비스 로그저장(Before commit) 실행", mainThread);

        Log logs = Log.builder().logMessage("아무거나").build();
        logRepository.save(logs);

        throwNPE("LogService에서 강제발생");
        log.info("[ThreadID={}] : Log서비스 로그저장(Before commit) 저장완료", mainThread);

    }
}
