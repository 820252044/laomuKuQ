package com.laomukuq.model.weather;

public class WeekWeather {

    // 日期
    private String date;
    // 高温
    private String high;
    // 低温
    private String low;
    // 今天日期
    private String ymd;
    // 周几
    private String week;
    // 日出
    private String sunrise;
    // 日落
    private String sunset;
    // 空气质量指数
    private String aqi;
    // 风向
    private String fx;
    // 风力
    private String fl;
    // 天气类型
    private String type;
    // 小贴士
    private String notice;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getYmd() {
        return ymd;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "WeekWeather{" +
                "date='" + date + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", ymd='" + ymd + '\'' +
                ", week='" + week + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                ", aqi='" + aqi + '\'' +
                ", fx='" + fx + '\'' +
                ", fl='" + fl + '\'' +
                ", type='" + type + '\'' +
                ", notice='" + notice + '\'' +
                '}';
    }
}
