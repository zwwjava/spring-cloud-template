package com.ww.yifeichief.service;

import com.ww.common.aop.WwException;
import com.ww.common.utils.StringTools;
import com.ww.yifeichief.bean.UserInfo;
import com.ww.yifeichief.dto.UserInfoDto;
import com.ww.yifeichief.repository.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/4/25 15:15
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean register(UserInfo userInfo) {
        userInfo.setId(StringTools.getUuid());
        //是否已经注册
        UserInfo userInfoOld = userRepository.findByMobil(userInfo.getMobil());
        if (userInfoOld != null) {
            throw WwException.commonRException("该用户已被注册");
        }

        userRepository.save(userInfo);
        return true;
    }

    @Override
    public UserInfo login(UserInfoDto userInfoDto) {

        UserInfo userInfo = userRepository.findByMobil(userInfoDto.getMobil());
        if (userInfo != null) {
            return userInfo;
        }
        throw WwException.commonRException("为找到匹配用户名和密码");

    }
}
