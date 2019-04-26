package com.ww.yifeichief.repository;

import com.ww.yifeichief.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/4/25 15:17
 */
public interface UserRepository extends JpaRepository<UserInfo, String> {
    @Override
    UserInfo save(UserInfo userInfo);

    UserInfo findByMobil(String mobil);
}
