package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class provides configuration for Feign clients.
 * Feign is a declarative web service client, which simplifies writing HTTP clients.
 */
@Configuration
public class FeignConfig {

    /**
     * This method provides a bean of type Retryer.
     * Retryer is a Feign component that controls how Feign will retry requests when an error occurs.
     *
     * @return a Retryer instance with a period of 100ms, max period of 1 second and max attempts of 3.
     */
    @Bean
    public Retryer myRetryer() {
        // return Retryer.NEVER_RETRY; // This line is commented out. If uncommented, Feign would not retry failed requests.
//        return new Retryer.Default(100, 1, 3);
        return Retryer.NEVER_RETRY;
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}