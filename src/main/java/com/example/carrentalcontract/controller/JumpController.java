package com.example.carrentalcontract.controller;

import com.example.carrentalcontract.annotation.NotNull;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.model.SysUser;
import com.example.carrentalcontract.entity.model.Vehicle;
import com.example.carrentalcontract.entity.model.VehicleType;
import com.example.carrentalcontract.sercive.UsersService;
import com.example.carrentalcontract.sercive.VehicleService;
import com.example.carrentalcontract.sercive.VehicleTypeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    @Resource
    private VehicleService vehicleService;
    @Resource
    private VehicleTypeService typeService;

    @GetMapping("/user/login")
    public String login(){
        return "login";
    }

    @NotNull(field = "username", name = "账号", statusCode = 701)
    @NotNull(field = "password", name = "密码", statusCode = 702)
    @PostMapping("/users/loginuser")
    public String loginuser(SysUser user, HttpServletRequest request,Model model) {
        Result<SysUser> login = usersService.login(user);
        if (login.getStatusCode() == 200){
            HttpSession session =  request.getSession();
            session.setAttribute("loginuser",login.getData());
            // List<Vehicle> vehicles = vehicleService.findTopRentByLimit(6).getData();
            // session.setAttribute("vehicleList",vehicles);
            // model.addAttribute("loginuser",login.getData());
            // ModelAndView mav = new ModelAndView("redirect:/index");
            // mav.addObject("loginuser",login.getData());
            return "redirect:/users/find/vehicle";
        }else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("redirect:/user/login");
            return "redirect:/user/login";
        }
    }

    @GetMapping("/users/find/vehicle")
    public String findVehicle(HttpServletRequest request,Model model) {
            HttpSession session =  request.getSession();
            List<Vehicle> vehicles = vehicleService.findTopRentByLimit(6).getData();
        List<VehicleType> types = typeService.selectAll().getData();
        session.setAttribute("vehicleList",vehicles);
        session.setAttribute("types",types);
            return "redirect:/index";
    }

    @PostMapping("/users/find/vehicle/all")
    @ResponseBody
    public List<Vehicle> findVehicleByType(@RequestBody Vehicle type,HttpServletRequest request) {
        HttpSession session =  request.getSession();
        List<Vehicle> vehicles = vehicleService.findVehicleByType(type).getData();
        session.setAttribute("vehicleList",vehicles);
        return vehicles;
    }

    @GetMapping("/user/reviews")
    public String reviews(@Param("id") Long id, HttpServletRequest request){
        System.out.println("======"+id);
        // System.out.println("======//"+request.getParameter("id"));
        Vehicle data = vehicleService.selectByPrimaryKey(id).getData();
        System.out.println(data);
        HttpSession session = request.getSession();
        if (data!=null) {
            session.setAttribute("vechicleDetail", data);
            return "redirect:/reviews";
        }else {
            return "redirect:/index";
        }
    }


    @GetMapping("/user/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/reviews")
    public String reviews(){
        return "reviews";
    }

    @GetMapping("/index")
    public String index(HttpServletRequest request){
        HttpSession session =  request.getSession();
        List<Vehicle> vehicles = vehicleService.findTopRentByLimit(6).getData();
        List<VehicleType> types = typeService.selectAll().getData();
        session.setAttribute("vehicleList",vehicles);
        session.setAttribute("types",types);
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
