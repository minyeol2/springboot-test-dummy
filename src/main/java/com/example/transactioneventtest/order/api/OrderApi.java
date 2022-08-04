package com.example.transactioneventtest.order.api;

import com.example.transactioneventtest.order.application.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderApi {

    private final OrderService orderService;

    /* 테스트 편의를 위해 get 호출 (dto는 서비스에서 임의 세팅)*/
    @GetMapping("/order/{test-case}")
    public ResponseEntity<?> getInboundList(@PathVariable("test-case") Integer testCase) throws Exception {
        switch (testCase.intValue()) {
            case 1:  //@Transactional + @EventListener
                orderService.completeOrderSync_1();
                break;
            case 2 : //@Transactional + @EventListener + @Async
                orderService.completeOrderAsync_1();
                break;
            case 3 : //@Transactional + @TransactionalEventListner + @Propagation.REQUIRES_NEW
                orderService.completeOrderSync_2();
                break;
            case 4 : //@Transactional + @TransactionalEventListner + @Async
                orderService.completeOrderAsync_2();
                break;
            case 5 : //@Transactional + @TransactionalEventListner(phase = BEFORE_COMMIT) //*default = AFTER_COMMIT
                orderService.publishEvenetBeforeCommit();
                break;
            case 6 : //번외(비동기 이벤트로 쓸만한 예제)
                orderService.sendMessageAsync();
                break;
            default: break;
        }
        log.info("[ThreadID={}]",Thread.currentThread() + " : Order controller 종료(Write Response)");
        return ResponseEntity.ok().body("done");
    }


}
