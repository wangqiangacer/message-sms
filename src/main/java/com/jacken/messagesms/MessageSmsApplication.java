package com.jacken.messagesms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan("com.jacken.messagesms.dao")
public class MessageSmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageSmsApplication.class, args);
    }

}
