package com.example.carrentalcontract.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName JumpController
 * @Description //TODO
 * @Author admin
 * @date 2021/1/1 22:42
 * @Version 1.0
 **/
@Controller
public class JumpController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/success")
    public String success(){
        return "success";
    }
    @GetMapping("/unauth")
    public String unauth(){
        return "unauth";
    }




}
