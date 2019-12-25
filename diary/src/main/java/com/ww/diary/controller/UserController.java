package com.ww.diary.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ww.diary.dto.ArcitleCreateReq;
import com.ww.diary.dto.MessageResp;
import com.ww.diary.entity.ArticleEntity;
import com.ww.diary.entity.ImagesEntity;
import com.ww.diary.service.ArticleService;
import com.ww.diary.service.ArticleServiceImpl;
import com.ww.diary.service.ImagesService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author zww
 * @since 2019-12-23
 */
@RestController
public class UserController extends BaseController {

    @Resource
    ArticleService articleService;

    @Resource
    ImagesService imagesService;

    /**
     * 日记列表
     * @return
     */
    @RequestMapping(value = "/article", method = RequestMethod.GET)
    @ResponseBody
    public MessageResp article(){
        return success(articleService.list());
    }

    /**
     * 查询图片信息列表
     * @return
     */
    @RequestMapping(value = "/images/{articleId}", method = RequestMethod.GET)
    @ResponseBody
    public MessageResp images(@PathVariable String articleId){
        return success(imagesService.list(new QueryWrapper<ImagesEntity>().eq("article_id", articleId)));
    }

    /**
     * 创建日记
     * @return
     */
    @RequestMapping(value = "/article/create", method = RequestMethod.POST)
    @ResponseBody
    public MessageResp createArticle(@RequestBody @Valid ArcitleCreateReq arcitleCreateReq){
        return success(articleService.createArticle(arcitleCreateReq));
    }

}

