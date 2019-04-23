package com.ww.order.controller;

import com.ww.order.bean.OrderMaster;
import com.ww.order.common.ResultMessager;
import com.ww.order.config.RedisConfig;
import com.ww.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/3/19 17:49
 */
@RestController
@RequestMapping("order")
@RefreshScope
public class OrderController {

    @Autowired
    private RedisConfig redisConfig;

    @Value("${server.port}")
    String port;


    @Autowired
    private OrderService orderService;

    @GetMapping("list")
    public ResultMessager list() {
        redisConfig.set("zww", "zwwnihaoya");
        List<OrderMaster> list = orderService.findByWId(0);
        ResultMessager result = new ResultMessager(list);
        result.setPort("端口号为：" + redisConfig.get("zww"));
        return result;
    }

}
