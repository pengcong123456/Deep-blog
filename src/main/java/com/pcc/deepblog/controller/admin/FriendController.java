package com.pcc.deepblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcc.deepblog.entity.FriendLink;
import com.pcc.deepblog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-14 21:28
 **/
@Controller
@RequestMapping("/admin")
public class FriendController {
    @Autowired
    FriendLinkService friendService;

    //跳转至友链页面
    @RequestMapping("/friendlinks")
    public String friend(Model model, HttpServletRequest request,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<FriendLink> list = friendService.listFriendLink();
        PageInfo<FriendLink> pageInfo = new PageInfo<FriendLink>(list);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("message", request.getParameter("message"));
        return "admin/friendlinks";
    }

    //跳转至友链新增页面
    @GetMapping("/friendlinks/input")
    public String input(Model model) {
        model.addAttribute("friendlink", new FriendLink());
        return "admin/friendlinks-input";
    }

    //新增友链
    @PostMapping("/friendlinks")
    public String saveFriendLink(FriendLink friendLink, RedirectAttributes attributes, Model model) {
        FriendLink type1 = friendService.getFriendLinkByBlogaddress(friendLink.getBlogaddress());
        String message = "";
        if (type1 != null) {
            message = "已存在该友链地址，请勿重复插入";
            model.addAttribute("message", message);
            model.addAttribute("friendlink", new FriendLink());
            return "admin/friendlinks-input";
        }
        friendLink.setCreateTime(new Date());
        int a = friendService.saveFriendLink(friendLink);
        if (a == 0) {
            message = "插入友链数据失败";
            attributes.addAttribute("message", message);
            return "redirect:/admin/friendlinks";
        } else {
            message = "插入友链数据成功";
            attributes.addAttribute("message", message);
            return "redirect:/admin/friendlinks";
        }
    }

    //跳转至友链编辑页面
    @RequestMapping("/friendlinks/{id}/input")
    public String inputFriendLink(@PathVariable Long id, Model model) {
        FriendLink friendlink = friendService.getFriendLink(id);
        model.addAttribute("friendlink", friendlink);
        return "admin/friendlinks-input";
    }

    //修改友链
    @PostMapping("/friendlinks/{id}")
    public String updateFriendLink(FriendLink friendLink, Model model, RedirectAttributes attributes) {
        String message = "";
        FriendLink type1 = friendService.getFriendLinkByBlogaddress(friendLink.getBlogaddress());
        if (type1 != null) {
            message = "已存在该友链地址，请勿重复插入";
            model.addAttribute("message", message);
            model.addAttribute("friendlink", friendLink);
            return "admin/friendlinks-input";
        }
        int a = friendService.updateFriendLink(friendLink);
        if (a == 0) {
            message = "修改友链数据失败";
            attributes.addAttribute("message", message);
        } else {
            message = "修改友链数据成功";
            attributes.addAttribute("message", message);
        }
        return "redirect:/admin/friendlinks";
    }

    @RequestMapping("/friendlinks/delete")
    public String deleteFriendLink(Long id, Model model) {
        String message = "";
        int a = friendService.deleteFriendLink(id);
        if (a == 0) {
            message = "删除友链数据失败";
        } else {
            message = "删除友链数据成功";
        }
        //删除操作后返回首页，携带数据
        PageHelper.startPage(1, 10);
        List<FriendLink> list = friendService.listFriendLink();
        PageInfo<FriendLink> pageInfo = new PageInfo<FriendLink>(list);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("message", message);
        return "admin/friendlinks :: centerFriendMessage";
    }


}
