package com.ww.product.service;

import com.ww.product.bean.WeatherInfo;
import com.ww.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description -
 * @Author zww
 * @Date 2019/3/13 16:06
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<WeatherInfo> findByWId(Integer id) {
        //发送mq消息
        log.info("seng MQ message");
        amqpTemplate.convertAndSend("myOrderQueue","product init mq message");
        return productRepository.findByWId(1);
    }
}
