package com.wxgzh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LoginController class
 *
 * @author BowenWang
 * @date 2019/08/09
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        System.out.println("LOGIN!!!");
        return "admin";
    }
}
