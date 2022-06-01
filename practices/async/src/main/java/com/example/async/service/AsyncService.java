package com.example.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {
    @Async("async-thread")
    public CompletableFuture<String> run() {
        log.trace("run() method called");
        return new AsyncResult(hello()).completable();
    }
    public String hello() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(2000);
                log.debug("Thread Sleep {}.......", i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "async hello";
    }
}
