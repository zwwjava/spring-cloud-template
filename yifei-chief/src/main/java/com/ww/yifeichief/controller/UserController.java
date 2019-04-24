package com.ww.yifeichief.controller;

import com.ww.common.resultMessager.ResultMessager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/4/23 15:22
 */
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("msg")
    public ResultMessager list(String code) {
        ResultMessager result = new ResultMessager(code);
        return result;
    }

}
