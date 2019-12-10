package com.laomukuq.filters;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.local.EventLocalSendMessage;


/**
 * @author laomu
 * @version 1.0
 * @description 过滤器
 * @date 2019-12-10
 */
public class TestFilter extends IcqListener {
    @EventHandler
    public void onAllLocalMessageEvent(EventLocalSendMessage event) // 监听所有发送消息的事件
    {
        // 获取消息
        String message = event.getMessage();
        message = message.replace("%prefix%", "!");
        // 设置消息
        event.setMessage(message);
    }
}
