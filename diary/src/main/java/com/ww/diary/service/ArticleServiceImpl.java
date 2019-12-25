package com.ww.diary.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ww.diary.dto.ArcitleCreateReq;
import com.ww.diary.dto.ImageCreateReq;
import com.ww.diary.entity.ArticleEntity;
import com.ww.diary.entity.ImagesEntity;
import com.ww.diary.mapper.ArticleMapper;
import com.ww.diary.mapper.ImagesMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ww.diary.utils.MyBeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 图片信息表 服务实现类
 * </p>
 *
 * @author zww
 * @since 2019-12-23
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements ArticleService {

    @Resource
    ArticleMapper articleMapper;

    @Resource
    ImagesMapper imagesMapper;

    /**
     * 创建日记
     * @param arcitleCreateReq
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ArticleEntity createArticle(@Valid ArcitleCreateReq arcitleCreateReq) {
        //保存日记
        ArticleEntity articleEntity = saveArticle(arcitleCreateReq);

        //保存图片信息
        saveImages(articleEntity.getId(), arcitleCreateReq.getImagesList());

        return articleEntity;
    }

    /**
     * 保存图片信息
     * @param id
     * @param imagesList
     */
    private void saveImages(Integer id, List<ImageCreateReq> imagesList) {
        for (ImageCreateReq image : imagesList) {
            ImagesEntity imagesEntity = new ImagesEntity();
            MyBeanUtils.copyProperties(image, imagesEntity);
            imagesEntity.setArticleId(id.toString());
            imagesMapper.insert(imagesEntity);
        }
    }

    /**
     * 保存日记
     * @param arcitleCreateReq
     * @return
     */
    private ArticleEntity saveArticle(@Valid ArcitleCreateReq arcitleCreateReq) {
        ArticleEntity articleEntity = new ArticleEntity();
        MyBeanUtils.copyProperties(arcitleCreateReq, articleEntity);
        articleEntity.setUrl(arcitleCreateReq.getImagesList().get(0).getUrl());
        articleMapper.insert(articleEntity);
        return articleEntity;
    }

}
