package com.laomukuq.listeners;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventPrivateMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.laomukuq.entity.HttpResponseEntity;
import com.laomukuq.model.news.News;
import com.laomukuq.model.news.NewsCode;
import com.laomukuq.model.news.NewsResponseModel;
import com.laomukuq.utils.HttpClientUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 新闻监听
 */
public class NewsListener extends IcqListener {


    /**
     * 私聊新闻
     *
     * @param event
     */
    @EventHandler
    public void onPMessage(EventPrivateMessage event) throws UnsupportedEncodingException {
        // 头条新闻
        String message = event.getMessage();
        if (message.length() < 8 && message.contains("新闻")) {
            message = message.substring(0, message.length() - 2);
            String encode = URLEncoder.encode(message, "UTF-8");
            HttpResponseEntity newResponseEntity = HttpClientUtils.get("http://127.0.0.1:8081/news?newsName=" + encode);
            if (newResponseEntity.getResponseEntity() != null) {
                NewsCode newsCode = JSONObject.parseObject(newResponseEntity.getResponseEntity().toJSONString(), NewsCode.class);
                HttpResponseEntity httpResponseEntity = HttpClientUtils.get("http://zhouxunwang.cn/data/?id=75&key=Vb7D+4YwGN3+jJmK+48yT2zFOwTgsJeZ/px16A&type=" + newsCode.getNewsType());
                // 把普通对象转换为json对象
                // 把普通对象转换为json字符串
                // 把json字符串转换为json对象
                NewsResponseModel newsResponseModel = JSON.parseObject(JSON.toJSONString(httpResponseEntity.getResponseEntity()), NewsResponseModel.class);

                List<News> news = JSONObject.parseArray(JSON.toJSONString(newsResponseModel.getResult().get("data")), News.class);

                StringBuffer content = new StringBuffer();
                for (int i = 0; i < 5; i++) {
                    content.append(news.get(i).getTitle() + "\n");
                }
                event.respond(content.toString());
            } else {
                event.respond("你写的啥啊,没找着!");
            }
        }
    }
}
