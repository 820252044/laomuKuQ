package com.laomukuq.model.weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherModel {

    private Date date;
    private Date time;
    private City cityInfo;
    private WeatherDetailModel data;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        this.date = sdf.parse(sdf.format(date));
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time = sdf.parse(sdf.format(time));
    }

    public City getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(City cityInfo) {
        this.cityInfo = cityInfo;
    }

    public WeatherDetailModel getData() {
        return data;
    }

    public void setData(WeatherDetailModel data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WeatherModel{" +
                "date=" + date +
                ", time=" + time +
                ", cityInfo=" + cityInfo +
                ", data=" + data +
                '}';
    }
}
