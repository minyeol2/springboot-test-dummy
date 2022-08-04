package com.example.transactioneventtest.log.repository;

import com.example.transactioneventtest.log.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {

}
