package com.example.carrentalcontract.controller;

import com.example.carrentalcontract.annotation.NotNull;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.sercive.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName JumpController
 * @Description //TODO
 * @Author admin
 * @date 2021/1/1 22:42
 * @Version 1.0
 **/
@Controller
public class JumpController {

    @Resource
    private UsersService usersService;

    @GetMapping("/user/login")
    public String login(){
        return "login";
    }

    @NotNull(field = "username", name = "账号", statusCode = 701)
    @NotNull(field = "password", name = "密码", statusCode = 702)
    @PostMapping("/api/v1/car/users/loginuser")
    public ModelAndView loginuser(SysUser user, HttpServletRequest request, Model model) {
        Result<SysUser> login = usersService.login(user);
        ModelAndView mav = new ModelAndView();
        if (login.getStatusCode() == 200){
            // HttpSession session =  request.getSession();
            // session.setAttribute("loginuser",login.getData());
            // model.addAttribute("loginuser",login.getData());
            mav.setViewName("redirect:/index");
            mav.addObject("loginuser",login.getData());
        }else {
            mav.setViewName("redirect:/user/login");
        }
        return mav;
    }

    @GetMapping("/user/signup")
    public String signup(){
        return "signup";
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
