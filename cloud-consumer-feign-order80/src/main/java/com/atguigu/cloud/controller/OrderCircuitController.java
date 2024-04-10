package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderCircuitController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public String myCurcuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

    public String myCircuitFallback(Integer id, Throwable t) {
        return "myCircuitFallback, 系统繁忙，请稍后再试啊啊啊啊啊啊啊啊啊！";
    }

    @GetMapping("/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
    public String myBulkhead(@PathVariable("id") Integer id) {
        return payFeignApi.myBulkhead(id);
    }

    public String myBulkheadFallback(Throwable t) {
        return "myBulkheadFallback, 超出最大并发数量限制，请稍后再试QAQ";
    }

    @GetMapping("/feign/pay/bulkheadpool/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadPoolFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkheadPool(@PathVariable("id") Integer id) {
        System.out.println(Thread.currentThread().getName() + "\t" + "----进入线程池");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "----准备离开");
        return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id));
    }

    // id不同才能触发，不知道为什么
    public CompletableFuture<String> myBulkheadPoolFallback(Integer id, Throwable t) {
        return CompletableFuture.supplyAsync(() -> "Bulkhead.Type.THREADPOOL myBulkheadPoolFallback " +
                "超出最大数量限制，系统繁忙，稍后再试！");
    }

    @GetMapping("/feign/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "myRateFallback")
    public String myRateLimiter(@PathVariable("id") Integer id) {
        return payFeignApi.myRatelimit(id);
    }

    public String myRateFallback(Integer id, Throwable t) {
        return "你被限流了，禁止访问qaq";
    }
}
