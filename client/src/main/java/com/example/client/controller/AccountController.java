package com.example.client.controller;

import com.example.client.entity.Admin;
import com.example.client.entity.User;
import com.example.client.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountFeign accountFeign;

    //登录
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession session){
        Object object = accountFeign.login(username, password, type);//三个参数去Fegin中查询，若存在返回一个object
        LinkedHashMap<String,Object> hashMap = (LinkedHashMap)object;
        String idStr = null;
        String result = null;//不存在的情况
        long id = 0;
        if (object == null){//重新登录即可
            result = "login";
        }else{//存在，分管理员和普通用户
            switch (type){
                case "user":
                    User user = new User();
                    idStr = hashMap.get("id")+""; //把int类型变成String类型
                    id = Long.parseLong(idStr);
                    String nickname = (String)hashMap.get("nickname");
                    user.setId(id);
                    user.setNickname(nickname);
                    session.setAttribute("user",user);
                    result = "index";
                    break;
                case "admin":
                    Admin admin = new Admin();
                    idStr = hashMap.get("id")+""; //把int类型变成String类型
                    id = Long.parseLong(idStr);
                    String username2 = (String)hashMap.get("username");
                    admin.setId(id);
                    admin.setUsername(username2);
                    session.setAttribute("admin",admin);
                    result = "main";
                    break;
            }
        }
        return result;
    }
    //退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }
}
