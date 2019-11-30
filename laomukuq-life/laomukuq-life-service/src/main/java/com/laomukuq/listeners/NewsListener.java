package com.laomukuq.listeners;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventPrivateMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.laomukuq.entity.HttpResponseEntity;
import com.laomukuq.model.news.News;
import com.laomukuq.model.news.NewsResponseModel;
import com.laomukuq.utils.HttpClientUtils;

import java.util.List;

/**
 * 新闻监听
 */
public class NewsListener extends IcqListener {


    /**
     * 私聊新闻
     * @param event
     */
    @EventHandler
    public void onPMessage(EventPrivateMessage event){
        // 头条新闻
        String message = event.getMessage();
        message = message.substring(0, message.length() - 2);
        if("头条".equals(message)) {
            message = "top";

        String url = "http://zhouxunwang.cn/data/?id=75&key=Vb7D+4YwGN3+jJmK+48yT2zFOwTgsJeZ/px16A&type=" + message;
        HttpResponseEntity httpResponseEntity = HttpClientUtils.get(url);
        // 把普通对象转换为json对象
        // 把普通对象转换为json字符串
        // 把json字符串转换为json对象
        NewsResponseModel newsResponseModel = JSON.parseObject(JSON.toJSONString(httpResponseEntity.getResponseEntity()), NewsResponseModel.class);
        Object data = newsResponseModel.getResult().get("data");
        List<News> news = JSONObject.parseArray(JSON.toJSONString(data), News.class);

        StringBuffer content = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            content.append(news.get(i).getTitle() + "\n");
        }

        System.out.println(content);
        String s = content.toString();
        event.respond(s);
        }
    }
}
