package com.laomukuq.service;

import com.laomukuq.mapper.CityCodeMapper;
import com.laomukuq.model.CityCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    @Autowired
    private CityCodeMapper cityCodeMapper;

    public Map<String, Integer> selectAll(String city){
        // 查询出所有的城市和编号
        List<CityCode> cityCodes = this.cityCodeMapper.selectAll();
        // 遍历集合判断有没有存在要查询的城市
        for (CityCode cityCode : cityCodes) {
            if(city.equals(cityCode.getCityName())){
                HashMap<String, Integer> map = new HashMap<>();
                map.put(city, cityCode.getCityCode());
                return map;
            }
        }
        return new HashMap<>();
    }
}
