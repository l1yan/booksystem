package com.example.client.controller;


import com.example.client.entity.BookList;
import com.example.client.entity.BookListVO;
import com.example.client.feign.BookListFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/booklist")
public class BookListController {

    @Autowired
    private BookListFeign bookListFeign;

    @GetMapping("/findAll")
    @ResponseBody
    public BookListVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return bookListFeign.findAll(index, limit);
    }
    @GetMapping({"/redirect/{location}"})
    public String redirect(@PathVariable("location") String location){
        return location;
    }

    @GetMapping({"/deleteById/{id}"})
    public String deleteById(@PathVariable("id") long id){
        bookListFeign.deleteById(id);
        return "redirect:/booklist/redirect/booklist_manage";
    }

    @GetMapping("/findTypes")
    public ModelAndView findTypes(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("booklist_add");
        modelAndView.addObject("list",bookListFeign.findTypes());
        return modelAndView;
    }
    @PostMapping("/save")
    public String save(BookList bookList){
        bookListFeign.save(bookList);
        return "redirect:/booklist/redirect/booklist_manage";
    }
    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("booklist_update");
        modelAndView.addObject("booklist",bookListFeign.findById(id));
        modelAndView.addObject("list",bookListFeign.findTypes());
        return modelAndView;
    }
    @PostMapping("/update")
    public String update(BookList bookList){
        bookListFeign.update(bookList);
        return "redirect:/booklist/redirect/booklist_manage";
    }
}
