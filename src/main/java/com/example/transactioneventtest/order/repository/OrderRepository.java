package com.example.transactioneventtest.order.repository;

import com.example.transactioneventtest.order.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
