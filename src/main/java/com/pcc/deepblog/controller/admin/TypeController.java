package com.pcc.deepblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcc.deepblog.entity.Type;
import com.pcc.deepblog.service.TypeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-14 16:28
 **/
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    TypeService typeService;

    //查询所有的博客类别
    @RequestMapping("/types")
    public String getAllType(Model model, HttpServletRequest request,
                             @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        //按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, 10, orderBy);
        List<Type> allType = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo", pageInfo);
        String message = request.getParameter("message");
        model.addAttribute("message", message);
        return "admin/types";
    }

    //新增分类
    @PostMapping("/types")
    public String saveType(Type type, Model model, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        String message = "";
        if (type1 != null) {
            message = "提示：不能添加重复的分类";
            attributes.addAttribute("message", message);
            return "redirect:/admin/types/input";
        }

        int a = typeService.saveType(type);
        if (a == 0) {
            message = "新增类型失败";
        } else {
            message = "新增类型成功";
        }
        attributes.addAttribute("message", message);
        return "redirect:/admin/types";
    }

    //跳转到新增类别页面
    @GetMapping("/types/input")
    public String typeInput(Model model, HttpServletRequest request) {
        String message = request.getParameter("message");
        model.addAttribute("type", new Type());
        model.addAttribute("message", message);
        return "admin/types-input";
    }

    //修改类别页面
    @GetMapping("/types/{id}/input")
    public String typeInput2(@PathVariable("id") Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    //修改类型
    @PostMapping("/types/{id}")
    public String updateType(Type type, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        String message = "";
        if (type1 != null) {
            message = "提示：不能添加重复的分类";
            attributes.addAttribute("message", message);
            return "redirect:/admin/types/input";
        }

        int a = typeService.updateType(type);
        if (a == 0) {
            message = "修改类型失败";
        } else {
            message = "修改类型成功";
        }
        attributes.addAttribute("message", message);
        return "redirect:/admin/types";
    }

    @RequestMapping("/types/delete")
    public String deleteType(Long id,Model model){
        int a= typeService.deleteType(id);
        String message="";
        if (a==0){
            message="删除类型失败";
        }else {
            message="删除类型成功";
        }
        model.addAttribute("message",message);

        //按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(1, 10, orderBy);
        List<Type> allType = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types :: centerTypeMessage";
    }

}
