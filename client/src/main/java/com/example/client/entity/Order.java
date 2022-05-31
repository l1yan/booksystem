package com.example.client.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private long id;
    private User user;
    private BookList bookList;
    private Admin admin;
    private Date date;
    private int state;
}
