package com.pcc.deepblog.controller;

import com.pcc.deepblog.entity.FriendLink;
import com.pcc.deepblog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-19 20:11
 **/
@Controller
public class FriendLinkController {
    @Autowired
    FriendLinkService friendlinks;

    @RequestMapping("/friends")
    public String GetFriends(Model model) {
        List<FriendLink> friendLinks = friendlinks.listFriendLink();
        model.addAttribute("friendlinks", friendLinks);
        return "friends";
    }
}
