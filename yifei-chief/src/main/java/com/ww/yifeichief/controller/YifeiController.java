package com.ww.yifeichief.controller;

import com.ww.common.aop.MessageResp;
import com.ww.common.aop.WwException;
import com.ww.common.componsents.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * @Description - 小程序相关接口
 * @Author 查旺旺
 * @Date 2019/5/7 15:35
 */
@RestController
@RequestMapping("yifei")
@Slf4j
public class YifeiController extends BaseController {


    /**
     * 文档文件上传腾讯云
     * @param file 上传的文档文件
     * @return 文档的HTTP访问地址
     */
    @PostMapping("/uploadToCos")
    public MessageResp uploadToCos(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw WwException.commonRException("上传文件不存在或内容为空");
        }
        String filename = file.getOriginalFilename();
        String suffix = StringUtils.EMPTY;
        if (filename.lastIndexOf(".") > -1) {
            suffix = filename.substring(filename.lastIndexOf("."));
        }

        return success();
    }
}
