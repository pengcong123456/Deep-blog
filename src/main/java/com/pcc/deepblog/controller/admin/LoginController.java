package com.pcc.deepblog.controller.admin;

import com.pcc.deepblog.entity.User;
import com.pcc.deepblog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author : 彭聪
 * @date : 2020-10-11 12:09
 **/

@Controller
@RequestMapping(value = "/admin")
public class LoginController {
    @Autowired
    AdminService adminService;

    @RequestMapping
    public String login() {
        return "admin/login";
    }

    @PostMapping("/loginback")
    public String loginback(String username, String password, Model model, HttpSession session) {
        username = username.trim();
        password = password.trim();
        String message = "";
        try {
            User user = adminService.loginback(username, password);
            session.setAttribute("user", user);
            return "admin/index";
        } catch (Exception e) {
            message = e.getMessage();
        }
        model.addAttribute("message", message);
        return "forward:/admin";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        //移除session中的信息
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
