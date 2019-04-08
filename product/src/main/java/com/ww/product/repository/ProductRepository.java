package com.ww.product.repository;

import com.ww.product.bean.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description -
 * @Author zww
 * @Date 2019/3/13 15:22
 */
public interface ProductRepository extends JpaRepository<WeatherInfo, Integer> {

    @Override
    List<WeatherInfo> findAll();

    List<WeatherInfo> findByWId(Integer id);
}
