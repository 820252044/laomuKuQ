package com.laomukuq.configuration;

import com.laomukuq.entity.LaomuBot;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author laomu
 * @version 1.0
 * @description 从yaml获取机器人集群配置
 * @date 2019-12-10
 */
@Component
@ConfigurationProperties(prefix = "laomubot")
public class BotAccountProperties {

    private List<LaomuBot> laomuBots;

    public List<LaomuBot> getLaomuBots() {
        return laomuBots;
    }

    public void setLaomuBots(List<LaomuBot> laomuBots) {
        this.laomuBots = laomuBots;
    }

    @Override
    public String toString() {
        return "BotAccountProperties{" +
                "laomuBots=" + laomuBots +
                '}';
    }
}
