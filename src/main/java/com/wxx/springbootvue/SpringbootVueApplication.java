package com.wxx.springbootvue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Wang
 */
@MapperScan(basePackages = "com.wxx.springbootvue.system.mapper")
@SpringBootApplication
public class SpringbootVueApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootVueApplication.class, args);
    }

}
