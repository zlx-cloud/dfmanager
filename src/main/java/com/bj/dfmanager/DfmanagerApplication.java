package com.bj.dfmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan(basePackages = "com.bj.dfmanager.mapper")
@SpringBootApplication
@EnableScheduling
public class DfmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DfmanagerApplication.class, args);
    }

}
