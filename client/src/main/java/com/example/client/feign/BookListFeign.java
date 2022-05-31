package com.example.client.feign;

import com.example.client.entity.BookList;
import com.example.client.entity.BookListVO;
import com.example.client.entity.Type;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "booklist")
public interface BookListFeign {

    @GetMapping("/booklist/findAll/{index}/{limit}")
    public BookListVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit);

    @DeleteMapping("/booklist/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id);

    @GetMapping("/booklist/findTypes")
    public List<Type> findTypes();

    @PostMapping("/booklist/save")
    public void save(@RequestBody BookList bookList);

    @GetMapping("/booklist/findById/{id}")
    public BookList findById(@PathVariable("id") long id);

    @PutMapping("/booklist/update")
    public void update(BookList bookList);
}
