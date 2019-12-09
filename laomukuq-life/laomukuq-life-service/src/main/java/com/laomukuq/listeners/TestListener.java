package com.laomukuq.listeners;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.sender.message.MessageBuilder;
import cc.moecraft.icq.sender.returndata.ReturnListData;
import cc.moecraft.icq.sender.returndata.returnpojo.get.RGroup;
import com.alibaba.fastjson.JSONObject;
import com.laomukuq.entity.HttpResponseEntity;
import com.laomukuq.model.weather.CityCode;
import com.laomukuq.model.weather.WeatherModel;
import com.laomukuq.utils.HttpClientUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

/**
 *
 * @author laomu
 */
public class TestListener extends IcqListener
{
    @EventHandler
    public void onPMEvent(EventGroupMessage event) throws UnsupportedEncodingException {
        String message = event.getMessage();
        // System.out.println("接到消息");

        // 纯属测试没有嘲讽意思啦...
        if (event.getMessage().contains("老冯鶸"))
        {
            event.respond("%prefix%!老冯鶸");
        }

        if(message.length() < 7 && message.contains("天气")){
            message = message.substring(0, message.length() - 2);
            String encode = URLEncoder.encode(message, "UTF-8");
            HttpResponseEntity httpResponseEntity = HttpClientUtils.get("http://127.0.0.1:8081/weather?city=" + encode);

            if(httpResponseEntity.getResponseEntity() != null){
            // 转换为CityCode
            CityCode cityCode = JSONObject.parseObject(httpResponseEntity.getResponseEntity().toJSONString(), CityCode.class);
            HttpResponseEntity responseEntity = HttpClientUtils.get("http://t.weather.sojson.com/api/weather/city/" + cityCode.getCityCode());
            JSONObject weatherEntity = responseEntity.getResponseEntity();
            WeatherModel weatherModel = JSONObject.parseObject(weatherEntity.toJSONString(), WeatherModel.class);
            String content = "-----------------\n" +
                             "|"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(weatherModel.getTime()) +"|\n" +
                             "|"+weatherModel.getCityInfo().getParent() + "省" + weatherModel.getCityInfo().getCity() +"|\n" +
                             "|"+ "最后更新时间为:" + weatherModel.getCityInfo().getUpdateTime()+"|\n" +
                             "|"+ "湿度为:" + weatherModel.getData().getShidu()+"|\n" +
                             "|"+ "pm25为:" + weatherModel.getData().getPm25()+"|\n" +
                             "|"+ "pm10为:" + weatherModel.getData().getPm10()+"|\n" +
                             "|"+ "空气质量:" + weatherModel.getData().getQuality()+"|\n" +
                             "|"+ "温度:" + weatherModel.getData().getWendu()+ "℃" + "|\n" +
                             "|"+ "感冒情况:" + weatherModel.getData().getGanmao()+"|\n";


            /*for (WeekWeather weekWeather : weatherModel.getData().getForecast()) {
                 content += "|"+ "日期:" + weekWeather.getYmd() +"|\n" +
                            "|"+ "" + weekWeather.getHigh()+"|\n" +
                            "|"+ "" + weekWeather.getLow()+"|\n" +
                            "|"+ "今天是:" + weekWeather.getWeek()+"|\n" +
                            "|"+ "日出时间:" + weekWeather.getLow()+"|\n" +
                            "|"+ "日落时间:" + weekWeather.getLow()+"|\n" +
                            "|"+ "空气质量指数:" + weekWeather.getAqi()+"|\n" +
                            "|"+ "风向:" + weekWeather.getFx()+"|\n" +
                            "|"+ "风力:" + weekWeather.getFl()+"|\n" +
                            "|"+ "天气类型:" + weekWeather.getType()+"|\n";
            }*/

             /*content += "|"+ "日期:" + weatherModel.getData().getYesterday().getYmd() +"|\n" +
                        "|"+ "" + weatherModel.getData().getYesterday().getHigh()+"|\n" +
                        "|"+ "" + weatherModel.getData().getYesterday().getLow()+"|\n" +
                        "|"+ "今天是:" + weatherModel.getData().getYesterday().getWeek()+"|\n" +
                        "|"+ "日出时间:" + weatherModel.getData().getYesterday().getLow()+"|\n" +
                        "|"+ "日落时间:" + weatherModel.getData().getYesterday().getLow()+"|\n" +
                        "|"+ "空气质量指数:" + weatherModel.getData().getYesterday().getAqi()+"|\n" +
                        "|"+ "风向:" + weatherModel.getData().getYesterday().getFx()+"|\n" +
                        "|"+ "风力:" + weatherModel.getData().getYesterday().getFl()+"|\n" +
                        "|"+ "天气类型:" + weatherModel.getData().getYesterday().getType()+"|\n";*/
            content += "-----------------";
            event.respond(content);
        }

        if (event.getMessage().contains("和宝宝说"))
        {
            event.getHttpApi().sendPrivateMsg(350970980, event.getMessage().split(":")[1]);
        }

        if (event.getMessage().equals("测试回复数据"))
        {
            testDataReturn(event.getBot());
        }

        if (event.getMessage().equals("测试MessageBuilder"))
        {
            event.respond(new MessageBuilder()
                    //图片组件需会员 .add(new ComponentImage("C:\\Users\\Administrator\\Desktop\\aaaa.gif")).newLine()
                    .toString());
        }
        }
    }

    /**
     * 用来测试回复数据的
     */
    public void testDataReturn(PicqBotX bot)
    {
        // 测试成功 #1: 普通泛型
        // ReturnData<RLoginInfo> returnData = bot.getAccountManager().getNonAccountSpecifiedApi().getLoginInfo();
        // bot.getLogger().log("Return Data = " + returnData);

        // 测试 #2: List泛型
        ReturnListData<RGroup> returnListData = bot.getAccountManager().getNonAccountSpecifiedApi().getGroupList();
        bot.getLogger().log("Return List Data = " + returnListData);
    }
}
