package com.xhn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xhn.**.mapper")
@SpringBootApplication
public class LifeMateMonolithApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifeMateMonolithApplication.class, args);
    }

}
