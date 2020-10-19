package com.pcc.deepblog.controller;

import com.pcc.deepblog.queryvo.BlogQuery;
import com.pcc.deepblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-19 19:16
 **/
@Controller
public class ArchivesController {
    @Autowired
    BlogService blogService;

    @RequestMapping("/archives")
    public String getArchives(Model model) {
        List<BlogQuery> blogs = blogService.getAllBlog();
        model.addAttribute("blogs", blogs);
        return "archives";

    }
}
