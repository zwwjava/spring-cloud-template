package com.ww.order.service;

import com.ww.order.bean.OrderMaster;

import java.util.List;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/3/19 17:50
 */
public interface OrderService {
    List<OrderMaster> findByWId(Integer id);
}
