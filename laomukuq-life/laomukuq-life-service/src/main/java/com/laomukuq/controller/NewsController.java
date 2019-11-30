package com.laomukuq.controller;

import com.laomukuq.model.news.NewsCode;
import com.laomukuq.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// Spring框架提供了
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    @ResponseBody
    public NewsCode get(String newsName){
        NewsCode newsCode = this.newsService.selectByName(newsName);
        System.out.println(newsCode);
        return newsCode;
    }


}
