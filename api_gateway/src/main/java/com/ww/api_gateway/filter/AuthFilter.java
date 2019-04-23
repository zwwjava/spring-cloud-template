package com.ww.api_gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/4/2 13:50
 */
@Component
public class AuthFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if ("login".equals(request.getRequestURI())) {
            System.out.println("登录校验");
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String wxCode =  request.getParameter("code");
        System.out.println("登录校验：" + wxCode);
        //获取 token 参数  也可以从header
        if (StringUtils.isEmpty(wxCode)) {
           requestContext.setSendZuulResponse(false);
           requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        requestContext.getResponse().addHeader("X-zww", UUID.randomUUID().toString());
        return null;
    }
}
