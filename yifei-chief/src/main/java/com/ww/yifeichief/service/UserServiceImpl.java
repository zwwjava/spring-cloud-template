package com.ww.yifeichief.service;

import com.ww.common.aop.WwException;
import com.ww.common.componsents.Auth;
import com.ww.common.utils.StringTools;
import com.ww.yifeichief.bean.UserInfo;
import com.ww.yifeichief.dto.UserInfoDto;
import com.ww.yifeichief.repository.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/4/25 15:15
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private Auth auth;

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
        if (userInfo == null) throw WwException.commonRException("为找到匹配用户名和密码");
        if (!userInfo.getPassword().equals(userInfoDto.getPassword())) throw WwException.commonRException("密码错误请检查重试");
        //登录成功
        auth.setUserInfo(userInfo.getId(), userInfo.getUsername(),userInfo.getOpenid(), userInfo.getMobil(), userInfo.getRole());
        return userInfo;


    }

    @Override
    public UserInfo queryUserInfo(String partyUuid) {
        UserInfo userInfo = userRepository.findUserInfoById(partyUuid );
        return userInfo;
    }
}
