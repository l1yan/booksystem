package com.example.client.entity;

import lombok.Data;

@Data
public class BookList {
    private long id;
    private String name;
    private double price;
    private String author;
    private Type type;
}
