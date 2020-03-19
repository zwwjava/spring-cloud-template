package com.ww.product.repository;

import com.ww.product.bean.WeatherInfo;
import com.ww.product.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findByWId() throws Exception {
        List<WeatherInfo> list = productMapper.selectList(null);
        System.out.println("nihao1\n\n\n\n\n\n\n\n\n\n");
//        List<WeatherInfo> list = productRepository.findByWId(1);
//        List<WeatherInfo> list = productRepository.findAll();
        System.out.println(list);

    }

}