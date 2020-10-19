package com.pcc.deepblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcc.deepblog.entity.Type;
import com.pcc.deepblog.queryvo.FirstPageBlog;
import com.pcc.deepblog.service.BlogService;
import com.pcc.deepblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-19 16:49
 **/
@Controller
public class TypeShowController {
    @Autowired
    TypeService typeService;
    @Autowired
    BlogService blogService;

    @RequestMapping("types/{id}")
    public String getTypes(@PathVariable Long id, Model model,
                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Type> types = typeService.getAllTypeAndBlog();
        if (id == -1) {
            id = types.get(0).getId();
        }
        model.addAttribute("types", types);
        List<FirstPageBlog> blogs=blogService.getByTypeId(id);
        PageHelper.startPage(pageNum,10);
        PageInfo<FirstPageBlog> pageInfo =new PageInfo<>(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTypeId",id);
        return "types";

    }
}
