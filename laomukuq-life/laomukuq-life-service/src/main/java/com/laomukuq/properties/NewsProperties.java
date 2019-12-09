package com.laomukuq.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author laomu
 * @date 2019-12-07
 * @version 1.0
 * 新闻配置
 */
@Component
@ConfigurationProperties(prefix = "news")
public class NewsProperties {

    /*@Value("${key}")
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }*/
}
