package com.ww.yifeichief.controller;

import com.ww.common.aop.MessageResp;
import com.ww.common.aop.RespCode;
import com.ww.common.componsents.BaseController;
import com.ww.common.resultMessager.ResultMessager;
import com.ww.yifeichief.bean.UserInfo;
import com.ww.yifeichief.dto.UserInfoDto;
import com.ww.yifeichief.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description - 用户相关模块
 * @Author 查旺旺
 * @Date 2019/4/23 15:22
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController extends BaseController{

    @Autowired
    UserService userService;

    /**
     * 用户登录
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public MessageResp login(UserInfoDto userInfo) {
        return success(userService.login(userInfo));
    }

    /**
     * 用户祖册
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public MessageResp register(UserInfo userInfo) {
        userService.register(userInfo);
        return success();
    }

    /**
     * 查询用户信息
     * @return
     */
    @RequestMapping(value = "/queryUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public MessageResp queryUserInfo() {
        userService.queryUserInfo(getUserInfo().getPartyUuid());
        return success();
    }

}
