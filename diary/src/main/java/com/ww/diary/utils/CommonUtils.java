package com.ww.diary.utils;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sun.deploy.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
public class CommonUtils {

    private static Random random = new Random();

    private static DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getTimeBasedId() {
        return getTimeBasedId(null);
    }

    public static String getTimeBasedId(String prefix) {
        if (prefix == null) {
            prefix = "FIDE";
        }
        return prefix + LocalDateTime.now().format(yyyyMMddHHmmss) + String.format("%06d", random.nextInt(999999));
    }

}
