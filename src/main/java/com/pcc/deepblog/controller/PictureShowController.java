package com.pcc.deepblog.controller;

import com.pcc.deepblog.entity.Picture;
import com.pcc.deepblog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-19 20:25
 **/
@Controller
public class PictureShowController {
    @Autowired
    PictureService pictureService;
    @RequestMapping("/picture")
    public String getPictureShow(Model model){
        List<Picture> pictures = pictureService.listPicture();

        model.addAttribute("pictures",pictures);
        return "picture";

    }

}
