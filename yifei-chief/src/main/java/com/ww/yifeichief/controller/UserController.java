package com.ww.yifeichief.controller;

import com.ww.common.aop.MessageResp;
import com.ww.common.aop.RespCode;
import com.ww.common.resultMessager.ResultMessager;
import com.ww.yifeichief.bean.UserInfo;
import com.ww.yifeichief.dto.UserInfoDto;
import com.ww.yifeichief.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/4/23 15:22
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public MessageResp login(UserInfoDto userInfo) {
        return success(userService.login(userInfo));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public MessageResp register(UserInfo userInfo) {
        System.out.println(userInfo);
        userService.register(userInfo);
        ResultMessager result = new ResultMessager(userInfo);
        return success(result);
    }

    protected static <T> MessageResp<T> success(T data) {
        return buildResp(data, new RespCode("100200", "成功"));
    }

    protected static <T> MessageResp<T> buildResp(T data, RespCode respEnum) {
        MessageResp<T> resp = new MessageResp<T>();
        resp.setMessage(respEnum);
        resp.setData(data);
        return resp;
    }

}
