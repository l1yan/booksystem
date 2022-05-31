package com.example.client.controller;

import com.example.client.entity.BookList;
import com.example.client.entity.Order;
import com.example.client.entity.OrderVO;
import com.example.client.entity.User;
import com.example.client.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderFeign orderFeign;

    @GetMapping("/save/{mid}")
    public String save(@PathVariable("mid") int mid, HttpSession session){
        User user = (User) session.getAttribute("user");
        Order order = new Order();
        order.setUser(user);
        BookList bookList = new BookList();
        bookList.setId(mid);
        order.setBookList(bookList);
        orderFeign.save(order);
        return "order";
    }

    @GetMapping("/findAllByUid")
    @ResponseBody
    public OrderVO findAllByUid(@RequestParam("page")int page,@RequestParam("limit") int limit, HttpSession session){
        User user = (User) session.getAttribute("user");
        int index = (page-1)*limit;
        OrderVO orderVO = orderFeign.findAllByUid(index,limit,user.getId());
        return orderVO;
    }

    @GetMapping("/findAllByState")
    @ResponseBody
    public OrderVO findAllByState(@RequestParam("page")int page,@RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return orderFeign.findAllByState(index,limit);
    }

    @GetMapping("/updateState/{id}")
    public String updateState(@PathVariable("id")long id){
        orderFeign.updateState(id);
        return "redirect:/booklist/redirect/order_handler";
    }
}
