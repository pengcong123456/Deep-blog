package com.pcc.deepblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : 彭聪
 * @date : 2020-10-19 19:25
 **/
@Controller
public class MusicController {
    @RequestMapping("/music")
    public String getMusic(){
        return "music";
    }
}
