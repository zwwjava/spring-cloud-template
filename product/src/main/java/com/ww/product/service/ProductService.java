package com.ww.product.service;

import com.ww.product.bean.WeatherInfo;

import java.util.List;

/**
 * @Description -
 * @Author zww
 * @Date 2019/3/13 15:56
 */
public interface ProductService {
    List<WeatherInfo> findByWId(Integer id);
}
