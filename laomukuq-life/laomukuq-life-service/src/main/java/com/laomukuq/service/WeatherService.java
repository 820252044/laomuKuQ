package com.laomukuq.service;

import com.laomukuq.mapper.CityCodeMapper;
import com.laomukuq.model.weather.CityCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author laomu
 * @date 2019-12-07
 * @version 1.0
 */
@Service
public class WeatherService {

    @Autowired
    private CityCodeMapper cityCodeMapper;

    public CityCode selectByName(String city){
        // 查询出城市对应的编号
        return this.cityCodeMapper.selectByName(city);
    }
}
