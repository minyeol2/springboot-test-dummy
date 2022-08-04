package com.example.transactioneventtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringbootTestDummyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTestDummyApplication.class, args);
    }

    public static void throwNPE(String msg) {
        throw new NullPointerException(msg);
    }

}
