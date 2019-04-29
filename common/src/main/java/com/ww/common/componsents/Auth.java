package com.ww.common.componsents;

import com.alibaba.fastjson.JSONObject;
import com.ww.common.aop.WwException;
import com.ww.common.bean.UserInfo;
import com.ww.common.enums.RespMessageEnum;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description - 用户登录信息组件
 * @Author 查旺旺
 * @Date 2019/4/2 13:50
 */
@Component
@Slf4j
public class Auth {

    /**
     * 匿名用户
     */
    private static final UserInfo ANONYMOUS_USER = new UserInfo("no_token_uuid");

    private static final int TOKEN_LIFE_IN_DAY = 7;

    private static final int MANAGER_LIFT_IN_MIN = 30;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public static boolean isAnonymousUser(String userUuid) {
        return ANONYMOUS_USER.getPartyUuid().equals(userUuid);
    }

    /**
     * 将用户信息存入redis
     * @param partyUuid
     * @return
     */
    public String setUserInfo(String partyUuid, String username, String openid, String mobil, String role) {
//        String token = partyUuid + "_" + UUID.randomUUID().toString();
        UserInfo userInfo = new UserInfo(partyUuid, username, openid, mobil, role);
        removeExist(partyUuid);
        redisTemplate.opsForValue().set(partyUuid, JSONObject.toJSONString(userInfo), TOKEN_LIFE_IN_DAY, TimeUnit.DAYS);

        return partyUuid;
    }

    /**
     * 将用户信息存入redis
     * @param partyUuid
     * @return
     */
    public String setUserInfo(String partyUuid, String username, String openid, String mobil, String role, int survivalTime, TimeUnit unit) {
        String token = partyUuid + "_" + UUID.randomUUID().toString();
        UserInfo userInfo = new UserInfo(partyUuid, username, openid, mobil, role);
        removeExist(partyUuid);
        redisTemplate.opsForValue().set(token, JSONObject.toJSONString(userInfo), survivalTime, unit);
        return token;
    }
    
    /**
     * 将用户信息存入redis (微信)
     * @param token
     * @param partyUuid
     * @return
     */
    public String updateUserInfo(String token, String partyUuid, String username, String openid, String mobil, String role) {
        UserInfo userInfo = new UserInfo(partyUuid, username, openid, mobil, role);
        delayToken(token, userInfo); //token每次使用时，自动延期
        //redisTemplate.opsForValue().set(token, JSONObject.toJSONString(userInfo), TOKEN_LIFE_IN_DAY, TimeUnit.DAYS);
        return token;
    }

    /**
     * 从redis获取用户信息
     * @return
     */
    public UserInfo getUserInfo() {
        return getUserInfo(false);//默认不允许匿名
    }

    /**
     * 从redis获取用户信息
     * @param anonymousable 是否允许匿名
     * @return
     */
    public UserInfo getUserInfo(boolean anonymousable) {
        String token = getToken();
        if (StringUtils.isEmpty(token)) {
            if (anonymousable) {
                return ANONYMOUS_USER;
            }
            log.warn("本次请求未携带所需的token参数");
            throw new WwException(RespMessageEnum.TOKEN_NOT_EXIST);
        }
        String userInfoStr = redisTemplate.opsForValue().get(token);
        if (StringUtils.isEmpty(userInfoStr)) {
            if (anonymousable) {
                return ANONYMOUS_USER;
            }
            throw new WwException(RespMessageEnum.TOKEN_NOT_EXIST);
        }
        delayToken(token, JSONObject.parseObject(userInfoStr, UserInfo.class)); //token每次使用时，自动延期
        //redisTemplate.opsForValue().set(token, userInfoStr, TOKEN_LIFE_IN_DAY, TimeUnit.DAYS);//token每次使用时，自动延期
        return JSONObject.parseObject(userInfoStr, UserInfo.class);
    }

    public void updateUserInfo(UserInfo userInfo) {
        String token = getToken();
        delayToken(token, userInfo);
        //redisTemplate.opsForValue().set(token, JSONObject.toJSONString(userInfo), TOKEN_LIFE_IN_DAY, TimeUnit.DAYS);
    }

    /**
     * 从redis移除用户信息
     */
    public UserInfo deleteUserInfo() {
        String token = getToken();
        String userInfoStr = redisTemplate.opsForValue().get(token);
        if (StringUtils.isEmpty(userInfoStr)) {
            throw new WwException(RespMessageEnum.TOKEN_NOT_EXIST);
        }
        redisTemplate.delete(token);
        return JSONObject.parseObject(userInfoStr, UserInfo.class);
    }

    private String getToken() {
        ServletRequestAttributes attrs = null;
        try {
            attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        } catch (Exception e) {
            log.info("非http请求");
            //log.error(e);
            return null;//临时兼容mq调用
        }
        String token = attrs.getRequest().getParameter("token");
        String tokenInHeader = attrs.getRequest().getHeader("token");//兼容tool中angular请求
        if (token == null) {
            token = tokenInHeader;
        }
        return token;
    }
    
    /**
     * 从redis获取用户信息
     * @return
     */
    public UserInfo getUserInfo(String token) {
        String userInfoStr = redisTemplate.opsForValue().get(token);
        if (StringUtils.isEmpty(userInfoStr)) {
            throw new WwException(RespMessageEnum.TOKEN_NOT_EXIST);
        }
        delayToken(token, JSONObject.parseObject(userInfoStr, UserInfo.class));//token每次使用时，自动延期
        return JSONObject.parseObject(userInfoStr, UserInfo.class);
    }

    /**
     * 移除旧登录token
     * @param uuid
     */
    private void removeExist(String uuid) {
        Set<String> keys = redisTemplate.keys(uuid + "_*");
        redisTemplate.delete(keys);
    }

    /**
     * token每次使用时，自动延期
     * @param token
     * @param userInfo
     */
    private void delayToken(String token, UserInfo userInfo){
        redisTemplate.opsForValue().set(token, JSONObject.toJSONString(userInfo), MANAGER_LIFT_IN_MIN, TimeUnit.MINUTES);//token每次使用时，自动延期
        /*if (userInfo.getType() == null){
            //fide_tool用户
            redisTemplate.opsForValue().set(token, JSONObject.toJSONString(userInfo), TOKEN_LIFE_IN_DAY, TimeUnit.DAYS);//token每次使用时，自动延期
        }else{
            switch (userInfo.getType()){
                case MANAGER:
                case MERCHANT:
                    //商家和后台管理人员延期30分钟
                    redisTemplate.opsForValue().set(token, JSONObject.toJSONString(userInfo), MANAGER_LIFT_IN_MIN, TimeUnit.MINUTES);//token每次使用时，自动延期
                    break;
                case CUSTOMER:
                case LITE:
                    //app用户延期7天
                    redisTemplate.opsForValue().set(token, JSONObject.toJSONString(userInfo), TOKEN_LIFE_IN_DAY, TimeUnit.DAYS);//token每次使用时，自动延期
                    break;
            }
        }*/
    }
}
