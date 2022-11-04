package com.evan.wj.controller;

import com.evan.wj.pojo.User;
import com.evan.wj.result.Result;
import com.evan.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

// 启动后端项目，run WjApplication.java
// 启动前端项目，Terminal->npm run dev
// 本页面实现了登录功能，同时运行前后端项目后，访问localhost:8080/#/login，输入用户名admin，密码123456后点击确定，即可进入localhost:8080/#/index

// https://github.com/Antabot/White-Jotter
// https://learner.blog.csdn.net/article/details/89294300

// 包含一个属性和userService和一个方法login，其中
// userService类提供了一个和“储存User类的MySQL表”进行数据交互的方法
// login函数被@PostMapping注释，表明将这个函数定位到/login页面上
// login函数的返回对象Result被@ResponseBody注释，表明这个函数的返回值会作为响应传递给前端
// login函数的输入对象requestUser被@RequestBody注释，表示这个对象是前端传递来的请求信息
// login中使用requestUser中的信息在User表中查找，若查找结果为null则返回code400，不是null则返回code200

// 用户的每次登录请求requestUser，login函数都会为该请求初始化一个空的session，并在login函数内部为该session对象添加属性user
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session) {
        User user = userService.get(
                HtmlUtils.htmlEscape(requestUser.getUsername()),
                requestUser.getPassword()
        );
        if (null == user) {
            return new Result(400);
        } else {
            // 新增了Session对象用以检查用户的登录状态(username-password)，若未登录则默认未空
            session.setAttribute("user", user);
            return new Result(200);
        }
    }
}

