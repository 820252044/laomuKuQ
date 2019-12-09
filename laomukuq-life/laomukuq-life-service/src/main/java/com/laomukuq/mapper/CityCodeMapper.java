package com.laomukuq.mapper;


import com.laomukuq.model.weather.CityCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author laomu
 * @date 2019-12-07
 * @version 1.0
 */
@Repository
public interface CityCodeMapper{

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(CityCode record);

    /**
     * 根据id删除
     * @param record
     * @return
     */
    int insertSelective(CityCode record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    CityCode selectByPrimaryKey(Integer id);

    /**
     * 根据id更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(CityCode record);

    /**
     * 根据id删除
     * @param record
     * @return
     */
    int updateByPrimaryKey(CityCode record);

    /**
     * 查询所有
     * @return List<CityCode>
     */
    List<CityCode> selectAll();

    /**
     * 根据城市名称搜索
     * @param cityName
     * @return CityCode
     */
    CityCode selectByName(@Param("cityName") String cityName);

}