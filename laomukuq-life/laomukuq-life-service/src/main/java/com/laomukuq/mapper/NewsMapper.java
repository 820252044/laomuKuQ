package com.laomukuq.mapper;

import com.laomukuq.model.news.NewsCode;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsMapper {

    NewsCode selectByName(String newsName);

}
