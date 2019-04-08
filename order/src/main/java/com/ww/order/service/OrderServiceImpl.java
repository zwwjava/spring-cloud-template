package com.ww.order.service;

import com.ww.order.bean.OrderMaster;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/3/19 17:50
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public List<OrderMaster> findByWId(Integer id) {
        return null;
    }
}
