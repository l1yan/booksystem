package com.example.booklist.repository;

import com.example.booklist.entity.BookList;

import java.util.List;

public interface BookListRepository {
    public List<BookList> findAll(int index, int limit);
    public int count();
    public BookList findById(long id);
    public void save(BookList bookList);
    public void update(BookList bookList);
    public void deleteById(long id);
}
