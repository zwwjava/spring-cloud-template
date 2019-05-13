package com.ww.api_gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.ww.common.componsents.Auth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * @Description - token验证，请求身份验证
 * @Author 查旺旺
 * @Date 2019/4/2 13:50
 */
@Component
@Slf4j
public class TokenFilter extends ZuulFilter {

    @Resource
    private Auth auth;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String s = request.getParameterMap().toString();
        log.info("request parameter: " +s);
        if ("/yifei-chief/user/register".equals(request.getRequestURI())) {
            log.info("登录校验");
            return false;
        }
        if ("/yifei-chief/user/login".equals(request.getRequestURI())) {
            log.info("登录校验");
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //获取 token 参数  也可以从header
        String token =  request.getHeader("token");
        log.info("token 值：" + token);
        if (StringUtils.isEmpty(token)) {
           requestContext.setSendZuulResponse(false);
           requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        //检查token是否失效
        auth.getUserInfo(token);
        log.info("token检查通过：" + token);
        return null;
    }
}
