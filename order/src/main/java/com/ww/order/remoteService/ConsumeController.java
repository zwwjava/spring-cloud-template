package com.ww.order.remoteService;

import com.ww.order.config.RestTemplateConfig;
import com.ww.order.feign.ProductFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/3/20 11:00
 */
@RestController
public class ConsumeController {

    @Value("${server.port}")
    String port;
//
//    @Autowired
//    LoadBalancerClient loadBalancerClient;
//
    @Autowired
    RestTemplate restTemplate;


    @Autowired
    ProductFeign productFeign;

    @GetMapping("getProductMsg")
    public String getProductMsg() {
        //第一种方式
//        RestTemplate client = new RestTemplate();
//        String msg = client.getForObject("http://localhost:8001/msg", String.class);
        //第二种方式
//        RestTemplate client = new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String productUrl = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/msg";
//        System.out.println(productUrl);
//        String msg = client.getForObject(productUrl, String.class);
        //第三种方式
        String msg = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//        String msg = productFeign.getProductList();
        return msg;
    }
}
