package com.laomukuq.controller;

import com.laomukuq.model.news.NewsCode;
import com.laomukuq.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author laomu
 * @date 2019-12-07
 * @version 1.0
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    @ResponseBody
    public NewsCode get(String newsName){
        return this.newsService.selectByName(newsName);
    }
}
