package com.laomukuq.model;

public class City {
    private String city;
    private Integer cityKey;
    private String parent;
    private String updateTime;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCityKey() {
        return cityKey;
    }

    public void setCityKey(Integer cityKey) {
        this.cityKey = cityKey;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                ", cityKey=" + cityKey +
                ", parent='" + parent + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
