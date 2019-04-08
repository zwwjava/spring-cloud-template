package com.ww.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/3/20 15:04
 */
@FeignClient(name = "product")
public interface ProductFeign {

    @GetMapping("msg")
    String getProductMsg();

    @GetMapping("product/list")
    String getProductList();

}
