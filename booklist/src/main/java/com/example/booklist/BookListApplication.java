package com.example.booklist;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.booklist.repository")
public class BookListApplication {
    public static void main(String[] args){
        SpringApplication.run(BookListApplication.class,args);
    }
}
