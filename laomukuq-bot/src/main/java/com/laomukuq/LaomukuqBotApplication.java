package com.laomukuq;

import cc.moecraft.icq.PicqBotX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author laomu
 * @version 1.0
 * @description 机器人启动类
 * @date 2019-12-10
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LaomukuqBotApplication {
    @Autowired
    private BuildBot buildBot;
    public static void main(String[] args) {
        SpringApplication.run(LaomukuqBotApplication.class, args);
        // 因为机器人微服务开启的是机器人得端口  不需要SpringBoot开启端口 所以以非web方式启动 只加载容器即可
        /*new SpringApplicationBuilder()
                .web(WebApplicationType.NONE)
                .sources(LaomukuqBotApplication.class)
                .run(args);*/
    }

    @Bean
    public List<PicqBotX> getPicqBotXList(){
        return buildBot.getPicqBotXList();
    }

    @Bean
    public ExecutorService getExecutorService(){
        return buildBot.getExecutorService();
    }

    @Bean(initMethod = "initBots")
    public BuildBot getBuileBot(){
        return new BuildBot();
    }
}
