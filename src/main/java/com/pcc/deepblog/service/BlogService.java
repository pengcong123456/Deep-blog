package com.pcc.deepblog.service;

import com.pcc.deepblog.entity.Blog;
import com.pcc.deepblog.queryvo.*;

import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-13 16:35
 **/
public interface BlogService {
    List<BlogQuery> getAllBlog() ;

    int getBlogTotal();

    int getBlogViewTotal();

    int getBlogCommentTotal();

    int getBlogMessageTotal();

    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);

    ShowBlog getBlogById(Long id);

    int saveBlog(Blog blog);

    int updateBlog(ShowBlog blog);

    int deleteBlog(Long id);

    List<FirstPageBlog> getAllFirstPageBlog();

    List<RecommendBlog> getRecommendedBlog();

    DetailedBlog getDetailedBlog(Long id);

    List<FirstPageBlog> getSearchBlog(String query);
}
