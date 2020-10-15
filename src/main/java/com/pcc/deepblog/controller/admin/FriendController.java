package com.pcc.deepblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcc.deepblog.entity.FriendLink;
import com.pcc.deepblog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
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
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,10);
        List<FriendLink> list=friendService.listFriendLink();
        PageInfo<FriendLink> pageInfo = new PageInfo<FriendLink>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("message",request.getParameter("message"));
        return "admin/friendlinks";
    }
    //跳转至友链新增页面
    @GetMapping("/friendlinks/input")
    public String input(Model model){
        model.addAttribute("friendlink",new FriendLink());
        return "admin/friendlinks-input";
    }
    //新增友链
    @PostMapping("/friendlinks")
    public String saveFriendLink(FriendLink friendLink, Model model, RedirectAttributes attributes){
        FriendLink type1=friendService.getFriendLinkByBlogaddress(friendLink.getBlogaddress());
        String message="";
        if (type1!=null){
            message="已存在该友链地址，请勿重复插入";
            model.addAttribute("message",message);
            return "admin/friendlinks-input :: ";
        }
        int a=friendService.saveFriendLink(friendLink);
        if (a==0){
            message="插入友链数据失败";
            model.addAttribute("message",message);
            return "admin/friendlinks-input :: ";
        }else {
            message="插入友链数据成功";
            attributes.addAttribute("message",message);
            return "redirect:/admin/friendlinks";
        }
    }
}
