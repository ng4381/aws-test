package com.example.awstest;

import com.example.awstest.domain.AssemblyOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.SessionScope;

@SpringBootApplication
@Slf4j
public class AwsTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsTestApplication.class, args);
    }

    @Bean
    @SessionScope
    public AssemblyOrder assemblyOrder() {
        log.info("Create ne bean of AssemblyOrder");
        return new AssemblyOrder();
    }
}
