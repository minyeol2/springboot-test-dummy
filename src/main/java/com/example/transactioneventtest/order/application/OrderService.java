package com.example.transactioneventtest.order.application;

import com.example.transactioneventtest.log.application.event.*;

import com.example.transactioneventtest.log.dto.LogDTO;
import com.example.transactioneventtest.order.domain.Order;
import com.example.transactioneventtest.order.dto.OrderDTO;
import com.example.transactioneventtest.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final ApplicationEventPublisher eventPublisher;

    private final OrderRepository orderRepository;

    @Transactional
    public void completeOrderSync_1() {
        Thread mainThread = Thread.currentThread();
        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 주문완료 메서드 실행");

        Order order = this.getOrderDTO().toEntity();
        orderRepository.save(order);
        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 주문 저장 완료");
        //throwNPE("OrderService에서 강제발생");

        String logMessage = String.format("주문상품 : [%d], 주문수량 : [%d],  비고내용 : [%s]",order.getOrderItemId(), order.getOrderQuantity(), order.getRemark());
        LogDTO logDTO = LogDTO.builder().referenceId(order.getId()).tableName("ORDERS").logMessage(logMessage).build();

        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 Log등록 이벤트 발행");
        eventPublisher.publishEvent(
                LogRegisterSyncEvent.builder()
                        .logDTO(logDTO)
                        .build()
        );
        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 주문완료 메서드 종료");
    }

    @Transactional
    public void completeOrderAsync_1() throws Exception {
        Thread mainThread = Thread.currentThread();
        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 주문완료 메서드 실행");

        Order order = this.getOrderDTO().toEntity();
        orderRepository.save(order);
        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 주문 저장 완료");
        //throwNPE("OrderService에서 강제발생")

        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 Log등록 이벤트 발행");
        eventPublisher.publishEvent(
                LogRegisterAsyncEvent.builder()
                        .referencdId(order.getId())
                        .build()
        );

        //Thread.sleep(3000);
        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 주문완료 메서드 종료");
    }

    @Transactional
    public void completeOrderSync_2() throws Exception {
        Thread mainThread = Thread.currentThread();
        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 주문완료 메서드 실행");

        Order order = this.getOrderDTO().toEntity();
        orderRepository.save(order);
        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 주문 저장 완료");
        //throwNPE("OrderService에서 강제발생");

        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 Log등록 이벤트 발행");
        eventPublisher.publishEvent(
                LogRegisterSyncTranEvent.builder()
                        .referencdId(order.getId())
                        .build()
        );
        //Thread.sleep(3000);
        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 주문완료 메서드 종료");
    }

    @Transactional
    public void completeOrderAsync_2() throws Exception {
        Thread mainThread = Thread.currentThread();
        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 주문완료 메서드 실행");

        Order order = this.getOrderDTO().toEntity();
        orderRepository.save(order);
        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 주문 저장 완료");
        //throwNPE("OrderService에서 강제발생");

        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 Log등록 이벤트 발행");
        eventPublisher.publishEvent(
                LogRegisterAsyncTranEvent.builder()
                        .referencdId(order.getId())
                        .build()
        );

        //Thread.sleep(3000);
        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 주문완료 메서드 종료");
    }

    @Transactional
    public void publishEvenetBeforeCommit() throws Exception {
        Thread mainThread = Thread.currentThread();
        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 주문완료 메서드 실행");

        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 Log등록 이벤트 발행");
        eventPublisher.publishEvent(new LogRegisterBeforeCommitEvent());

        Order order = this.getOrderDTO().toEntity();
        orderRepository.save(order);
        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 주문 저장 완료");

        //Thread.sleep(3000);
        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 주문완료 메서드 종료");
    }


    @Transactional
    public void sendMessageAsync() throws Exception {
        Thread mainThread = Thread.currentThread();
        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 주문완료 메서드 실행");

        //save order 6
        List<String> messageList = Arrays.asList("대니스", "민", "보보", "손", "에릭", "침");

        //throwNPE("OrderService에서 강제발생");

        log.info("[ThreadID={}]",mainThread.getId() + " : Order서비스 메시지 발송 이벤트 발행");
        int i = 0;
        for(String msg : messageList){
            i++;
            eventPublisher.publishEvent(
                    SendMessageEvent.builder()
                            .message("["+ i +"]" + msg + "님 주문완료!")
                            .build()
            );
        }


        //Thread.sleep(3000);
        log.info("[ThreadID={}]",Thread.currentThread() + " : Order서비스 주문완료 메서드 종료");
    }

    private OrderDTO getOrderDTO(){
        return OrderDTO.builder()
                    .orderItemId(1001L)
                    .orderQuantity(1)
                    .remark("사랑해요 침")
                    .build();
    }
}
