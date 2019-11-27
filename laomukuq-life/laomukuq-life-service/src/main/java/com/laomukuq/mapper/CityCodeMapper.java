package com.laomukuq.mapper;


import com.laomukuq.model.CityCode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityCodeMapper{

    int deleteByPrimaryKey(Integer id);

    int insert(CityCode record);

    int insertSelective(CityCode record);

    CityCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CityCode record);

    int updateByPrimaryKey(CityCode record);

    List<CityCode> selectAll();

}