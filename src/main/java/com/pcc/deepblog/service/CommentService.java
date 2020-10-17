package com.pcc.deepblog.service;

import com.pcc.deepblog.entity.Comment;

import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-16 20:28
 **/

public interface CommentService {
     List<Comment> listCommentByBlogId(Long id);

     int saveComment(Comment comment);
}
