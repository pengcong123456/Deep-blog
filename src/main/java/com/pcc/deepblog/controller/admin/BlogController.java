package com.pcc.deepblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcc.deepblog.entity.Blog;
import com.pcc.deepblog.entity.Type;
import com.pcc.deepblog.entity.User;
import com.pcc.deepblog.queryvo.BlogQuery;
import com.pcc.deepblog.queryvo.SearchBlog;
import com.pcc.deepblog.queryvo.ShowBlog;
import com.pcc.deepblog.service.BlogService;
import com.pcc.deepblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.PublicKey;
import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-13 16:29
 **/
@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    //调传到博客管理首页页面
    @RequestMapping("/blogs")
    public String blogs(HttpServletRequest request,
                        Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        //按照排序字段 倒序 排序
        String message = request.getParameter("message");
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 10, orderBy);
        List<BlogQuery> list = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<BlogQuery>(list);
        //获取到所有的Type，放回前端页面搜索框
        model.addAttribute("types", typeService.getAllType());
        //获取所有的文章简略信息
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("message", message);
        return "admin/blogs";
    }

    //博客新增
    @PostMapping("/blogs")
    public String insertBlog(Blog blog, HttpSession session,
                             RedirectAttributes redirectAttributes) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        int i = blogService.saveBlog(blog);
        String message = "";
        if (i == 0) {
            message = "新增Blog失败";
            redirectAttributes.addAttribute("message", message);
        } else {
            message = "新增Blog成功";
            redirectAttributes.addAttribute("message", message);
        }
        return "redirect:/admin/blogs";
    }

    //通过标题和类型id查询博客
    @RequestMapping("/blogs/search")
    public String blogSerch(SearchBlog searchBlog, Model model,
                            @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<BlogQuery> list = blogService.getBlogBySearch(searchBlog);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs :: blogList";
    }

    //利用页号查询当前页的数据
    @RequestMapping("/blogs/searchPage")
    public String searchPage(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {

        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 10, orderBy);
        List<BlogQuery> list = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<BlogQuery>(list);

        //获取所有的文章简略信息
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs :: blogList";
    }

    //新增文章页面
    @GetMapping("/blogs/input")
    public String blogInput(Model model) {
        //前台判断如果blog为空，走新增，若果不为空，走编辑blog
        model.addAttribute("blog", new Blog());
        model.addAttribute("types", typeService.getAllType());
        return "admin/blogs-input";
    }

    // 跳转编辑修改文章页面
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        ShowBlog blogById = blogService.getBlogById(id);
        List<Type> allType = typeService.getAllType();
        model.addAttribute("blog", blogById);
        model.addAttribute("types", allType);
        return "admin/blogs-input";
    }

    //提交修改
    @PostMapping("/blogs/{id}")
    public String updateBlog(ShowBlog blog,  RedirectAttributes redirectAttributes){
        int a =blogService.updateBlog(blog);
        String message="";
        if (a==0){
            message="文章修改失败";
        }else {
            message="文章修改成功";
        }
        redirectAttributes.addAttribute("message",message);
        return "redirect:/admin/blogs";
    }
    //通过博客id删除博客
    @RequestMapping("/blogs/delete")
    public String deleteBlog(Long id,Model model){
        String message="";
        int a=blogService.deleteBlog(id);
        if (a==0){
            message="删除博客"+id+"失败";
        }else {
            message="删除博客"+id+"成功";
        }
        //删除完毕后默认回到第一页
        String orderBy = "update_time desc";
        PageHelper.startPage(1, 10, orderBy);
        List<BlogQuery> list = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<BlogQuery>(list);

        //获取所有的文章简略信息
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("message", message);
        return "admin/blogs :: centerListMessage";
    }
}
