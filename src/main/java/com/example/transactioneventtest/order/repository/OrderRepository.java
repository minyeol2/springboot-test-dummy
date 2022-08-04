package com.example.transactioneventtest.order.repository;

import com.example.transactioneventtest.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
