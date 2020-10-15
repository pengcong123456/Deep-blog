package com.pcc.deepblog.controller;

import com.pcc.deepblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : 彭聪
 * @date : 2020-10-13 14:42
 **/
@Controller
public class IndexController {
    @Autowired
    BlogService blogService;

    @RequestMapping("/footer/blogmessage")
    public String blogmessage(Model model) {
        int blogTotal = blogService.getBlogTotal();
        int blogViewTotal = blogService.getBlogViewTotal();
        int blogCommentTotal = blogService.getBlogCommentTotal();
        int blogMessageTotal = blogService.getBlogMessageTotal();
        model.addAttribute("blogTotal", blogTotal);
        model.addAttribute("blogViewTotal", blogViewTotal);
        model.addAttribute("blogCommentTotal", blogCommentTotal);
        model.addAttribute("blogMessageTotal", blogMessageTotal);
        return "index :: blogMessage";
    }
}
