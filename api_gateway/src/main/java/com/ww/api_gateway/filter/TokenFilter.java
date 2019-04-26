package com.ww.api_gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/4/2 13:50
 */
@Component
public class TokenFilter extends ZuulFilter {
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
        System.out.println("request parameter: " +s);
        System.out.println(request.getRequestURI());
        if ("/yifei-chief/user/register".equals(request.getRequestURI())) {
            System.out.println("登录校验");
            return false;
        }
        if ("/yifei-chief/user/login".equals(request.getRequestURI())) {
            System.out.println("登录校验");
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        request.getHeader("token");
        //获取 token 参数  也可以从header
//        String token =  request.getParameter("token");
        String token =  request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
           requestContext.setSendZuulResponse(false);
           requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
