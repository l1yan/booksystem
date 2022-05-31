package com.example.booklist.controller;

import com.example.booklist.entity.BookList;
import com.example.booklist.entity.BookListVO;
import com.example.booklist.entity.Type;
import com.example.booklist.repository.BookListRepository;
import com.example.booklist.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booklist")
public class BookListController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private BookListRepository bookListRepository;
    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("/index")
    public String index(){
        return this.port;
    }

    @GetMapping("/findAll/{index}/{limit}")
    public BookListVO findAll(@PathVariable("index") int index,@PathVariable("limit") int limit){
        return new BookListVO(0,"",bookListRepository.count(),bookListRepository.findAll(index, limit));
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        bookListRepository.deleteById(id);
    }

    @GetMapping("/findTypes")
    public List<Type> findTypes(){
        return typeRepository.findAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody BookList bookList){
        bookListRepository.save(bookList);
    }

    @GetMapping("/findById/{id}")
    public BookList findById(@PathVariable("id") long id){
        return bookListRepository.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody BookList bookList){
        bookListRepository.update(bookList);
    }
}
