package com.ww.diary.service;

import com.ww.diary.dto.ArcitleCreateReq;
import com.ww.diary.entity.ArticleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 图片信息表 服务类
 * </p>
 *
 * @author zww
 * @since 2019-12-23
 */
public interface ArticleService extends IService<ArticleEntity> {

    ArticleEntity createArticle(@Valid ArcitleCreateReq arcitleCreateReq);

}
