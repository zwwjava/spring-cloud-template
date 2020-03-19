package com.ww.yifeichief.service;

import com.alibaba.fastjson.JSONObject;
import com.ww.common.aop.WwException;
import com.ww.common.bean.WxLoginInfo;
import com.ww.common.componsents.Auth;
import com.ww.common.utils.HttpsUtils;
import com.ww.common.utils.StringTools;
import com.ww.yifeichief.bean.UserInfo;
import com.ww.yifeichief.dto.UserInfoDto;
import com.ww.yifeichief.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/4/25 15:15
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private final static String url = "https://api.weixin.qq.com/sns/jscode2session";

    private final static String appId = "wxdaf6a3891cd1c6ff";//TODO 提交前修改

    private final static String secret = "0cfcb7e1b0dd9f2134f94318de136b6d"; //TODO 提交前修改

    private final static String grant_type = "authorization_code";

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

    @Override
    public void wxLogin(String code) {
        String resp = null;
        try {
            //请求腾讯 获得 openId
            resp = HttpsUtils.get(url + "?appid=" + appId + "&secret=" + secret + "&js_code=" + code + "&grant_type=" + grant_type);
        } catch (IOException e) {
            throw WwException.commonRException("获取微信用户信息失败");
        }
        JSONObject json = JSONObject.parseObject(resp);
        WxLoginInfo obj = json.toJavaObject(WxLoginInfo.class);
        auth.setWxUserInfo(obj);
        log.info("wx resp:" + resp);
    }
}
