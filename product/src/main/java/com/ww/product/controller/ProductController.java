package com.ww.product.controller;

import com.ww.product.common.ResultMessager;
import com.ww.product.bean.WeatherInfo;
import com.ww.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @Description -
 * @Author zww
 * @Date 2019/3/13 14:50
 */
@RestController
@RequestMapping("product")
@RefreshScope
public class ProductController {

    @Value("${server.port}")
    String port;

    @Value("${dev.name}")
    String name;

    @Autowired
    private ProductService productService;

    @GetMapping("list")
    public ResultMessager list() {
        List<WeatherInfo> list = productService.findByWId(0);
        ResultMessager result = new ResultMessager(list);
        result.setPort(name + "端口号为：" + port);
        return result;
    }

}
