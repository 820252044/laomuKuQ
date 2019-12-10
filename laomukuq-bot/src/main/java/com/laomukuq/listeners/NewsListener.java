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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author laomu
 * @date 2019-12-07
 * @version 1.0
 * 新闻监听
 */
@Component
public class NewsListener extends IcqListener {

    @Value("${news.key}")
    private String newsKey;
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
            HttpResponseEntity newResponseEntity = HttpClientUtils.get("http://127.0.0.1:9001/news?newsName=" + encode);
            if (newResponseEntity.getResponseEntity() != null) {
                NewsCode newsCode = JSONObject.parseObject(newResponseEntity.getResponseEntity().toJSONString(), NewsCode.class);
                if(newsCode.getNewsType() == null){
                    event.respond("没有相关的新闻类型");
                    return;
                }
                HttpResponseEntity httpResponseEntity = HttpClientUtils.get("http://zhouxunwang.cn/data/?id=" + newsKey + "&type=" + newsCode.getNewsType());
                // 把普通对象转换为json对象
                // 把普通对象转换为json字符串
                // 把json字符串转换为json对象
                NewsResponseModel newsResponseModel = JSON.parseObject(JSON.toJSONString(httpResponseEntity.getResponseEntity()), NewsResponseModel.class);

                List<News> news = JSONObject.parseArray(JSON.toJSONString(newsResponseModel.getResult().get("data")), News.class);

                StringBuilder content = new StringBuilder();
                for (int i = 0; i < 5; i++) {
                    content.append(news.get(i).getTitle()).append("\n");
                    content.append(news.get(i).getUrl()).append("\n");
                }
                event.respond(content.toString());
            } else {
                event.respond("没有相关的新闻类型");
            }
        }
    }
}
