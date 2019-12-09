package com.laomukuq.mapper;

import com.laomukuq.model.news.NewsCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author laomu
 * @date 2019-12-07
 * @version 1.0
 */
@Repository
public interface NewsMapper {

    /**
     * 根据名称搜索新闻类型
     * @param newsName
     * @return
     */
    NewsCode selectByName(@Param("newsName") String newsName);

}
