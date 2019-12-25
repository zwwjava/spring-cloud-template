package com.ww.diary.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.ww.diary.utils.CommonUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description:
 * Date： 19-6-19.
 *
 * @author zww
 */
@Component
public class FideMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(metaObject.getOriginalObject().getClass());
        if (tableInfo.getTableName().startsWith("rc_")) {
            _insertFill(metaObject, getFieldValByName("uuid", metaObject));
        } else {
            _insertFill(metaObject, null);
        }
    }

    private void _insertFill(MetaObject metaObject, Object uuid) {
        Date now = new Date();
        this.setFieldValByName("uuid", uuid != null ? uuid : CommonUtils.getUuid(), metaObject);
        this.setFieldValByName("createTime", now, metaObject);
        this.setFieldValByName("lastUpdateTime", now, metaObject);
        this.setFieldValByName("version", 0, metaObject);
        this.setFieldValByName("delFlag", false, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = new Date();
        this.setFieldValByName("lastUpdateTime", now, metaObject);
    }

}
