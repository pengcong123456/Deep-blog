package com.pcc.deepblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : 彭聪
 * @date : 2020-10-19 20:34
 **/
@Controller
public class AboutMeController {
    @RequestMapping("/about")
    public String about(){
        return "about";
    }
}
