package com.laomukuq.listeners;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import org.springframework.stereotype.Component;

/**
 * @author laomu
 * @version 1.0
 * @description 身份证查询
 * @date 2019-12-10
 */
@Component
public class IdCodeListener extends IcqListener {

    @EventHandler
    public void idCodeMessage(){
        // 先确定 私聊/群聊/讨论组
        // 申请接口
        // 确定想要调用的接口 key
        // 通过postman 拼接参数 调用接口
        // 分析返回的json串
        // 构建model
        // 发送Http请求获取返回信息
        // 解析json
        // 拼接为合适的字符串返回QQ
    }








}
