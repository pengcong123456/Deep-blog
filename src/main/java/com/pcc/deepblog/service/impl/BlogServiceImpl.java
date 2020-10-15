package com.pcc.deepblog.service.impl;

import com.pcc.deepblog.dao.BlogDao;
import com.pcc.deepblog.entity.Blog;
import com.pcc.deepblog.queryvo.BlogQuery;
import com.pcc.deepblog.queryvo.SearchBlog;
import com.pcc.deepblog.queryvo.ShowBlog;
import com.pcc.deepblog.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-13 16:39
 **/
@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    BlogDao blogDao;

    @Override
    public List<BlogQuery> getAllBlog() {
        return blogDao.getAllBlogQuery();
    }

    @Override
    public int getBlogTotal() {
        return blogDao.getBlogTotal();

    }

    @Override
    public int getBlogViewTotal() {
        Integer blogViewTotal = blogDao.getBlogViewTotal();
        if (blogViewTotal==null){
            return 0;
        }else {
            return blogViewTotal;
        }
    }

    @Override
    public int getBlogCommentTotal() {
        return blogDao.getBlogCommentTotal();
    }

    @Override
    public int getBlogMessageTotal() {

        return blogDao.getBlogMessageTotal();
    }

    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogDao.searchByTitleOrTypeOrRecommend(searchBlog);
    }

    /**
     *
     * @param id 博客的文章id
     * @return 博客编辑内容
     */
    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public int saveBlog(Blog blog) {
        return blogDao.saveBlog(blog);
    }

    /**
     *
     * @param blog 文章详情
     * @return 修改文章
     */
    @Override
    public int updateBlog(ShowBlog blog) {
        return blogDao.updateBlog(blog);
    }

    /**
     *
     * @param id 要删除的博客id
     * @return 删除博客
     */
    @Override
    public int deleteBlog(Long id) {
        return blogDao.deleteBlog(id);
    }
}
