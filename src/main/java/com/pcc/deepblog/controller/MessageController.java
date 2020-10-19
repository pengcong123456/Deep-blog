package com.pcc.deepblog.controller;

import com.pcc.deepblog.entity.Comment;
import com.pcc.deepblog.entity.Message;
import com.pcc.deepblog.entity.User;
import com.pcc.deepblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-19 19:28
 **/
@Controller
public class MessageController {

    @Autowired
    MessageService messageService;
    @Value("${message.avatar}")
    private String avatar;


    @GetMapping("/message")
    public String getMessage(Model model) {
        return "message";
    }

    //    查询留言
    @GetMapping("/messagecomment")
    public String messages(Model model) {
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message::messageList";
    }

    //    新增留言
    @PostMapping("/messages")
    public String post(Message message, HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        if (user != null) {
            message.setAvatar(user.getAvatar());
            message.setAdminMessage(true);
        } else {
            //设置头像
            message.setAvatar(avatar);
        }

        if (message.getParentMessage().getId() != null) {
            message.setParentMessageId(message.getParentMessage().getId());
        }
        messageService.saveMessage(message);
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message::messageList";
    }

    //删除留言，管理员权限
    @GetMapping("/messages/{id}/delete")
    public String deleteMessage(@PathVariable Long id) {
        int a = messageService.deleteMessage(id);

        return "redirect:/message";
    }

}
