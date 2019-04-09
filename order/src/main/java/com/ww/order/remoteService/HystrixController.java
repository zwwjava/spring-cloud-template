package com.ww.order.remoteService;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/4/3 15:04
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    @Autowired
    RestTemplate restTemplate;

//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
//    })
    @HystrixCommand
    @GetMapping("getMessage")
    String getMessage() {
//        throw new RuntimeException("超时了");
        String msg = restTemplate.getForObject("http://PRODUCT/msg", String.class);
        return msg;
    }

    private String fallback() {
        return "网络开小差了";
    }

    private String defaultFallback() {
        return "默认返回异常";
    }

}
