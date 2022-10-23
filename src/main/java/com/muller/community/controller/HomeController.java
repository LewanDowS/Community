package com.muller.community.controller;

import com.muller.community.Service.DiscussPostService;
import com.muller.community.Service.UserService;
import com.muller.community.entity.DiscussPost;
import com.muller.community.entity.Page;
import com.muller.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {  // 通过model携带数据给模板
        // 方法调用前,SpringMVC会自动实例化Model和Page,并将Page注入Model.
        // 所以,在thymeleaf中可以直接访问Page对象中的数据.
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");
        // list保存查询到的结果，并将结果转化为map键值对
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                // 帖子内容
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                // 把用户信息也查出来
                map.put("user", user);
                discussPosts.add(map);
            }
        }
        // 把list放入model发给模板，模板是index.html
        model.addAttribute("discussPosts", discussPosts);
        return "/index";
    }
}
