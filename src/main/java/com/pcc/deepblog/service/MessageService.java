package com.pcc.deepblog.service;

import com.pcc.deepblog.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> listMessage();

    void saveMessage(Message message);

    int deleteMessage(Long id);
}
