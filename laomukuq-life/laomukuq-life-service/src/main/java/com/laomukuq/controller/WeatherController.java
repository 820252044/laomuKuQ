package com.laomukuq.controller;

import com.laomukuq.model.weather.CityCode;
import com.laomukuq.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author laomu
 * @date 2019-12-07
 * @version 1.0
 */
@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public @ResponseBody
    CityCode get(String city){
        return this.weatherService.selectByName(city);
    }
}
