package com.ww.diary.service;

import com.ww.diary.entity.ImagesEntity;
import com.ww.diary.mapper.ImagesMapper;
import com.ww.diary.service.ImagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图片信息表 服务实现类
 * </p>
 *
 * @author zww
 * @since 2019-12-23
 */
@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, ImagesEntity> implements ImagesService {

}
