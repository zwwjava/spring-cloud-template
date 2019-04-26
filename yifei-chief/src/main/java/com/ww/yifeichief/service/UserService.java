package com.ww.yifeichief.service;

import com.ww.yifeichief.bean.UserInfo;
import com.ww.yifeichief.dto.UserInfoDto;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/4/25 15:14
 */
public interface UserService {

    boolean register(UserInfo userInfo);

    UserInfo login(UserInfoDto userInfo);

}
