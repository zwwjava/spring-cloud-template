package com.ww.order.repoistory;

import com.ww.order.bean.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/3/19 17:53
 */
public interface OrderRepository extends JpaRepository<OrderMaster, String> {
    @Override
    List<OrderMaster> findAll();
}
