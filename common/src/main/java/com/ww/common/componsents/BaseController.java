/**
 * www.yuanbaopu.com
 */
package com.ww.common.componsents;

import com.ww.common.aop.MessageResp;
import com.ww.common.aop.RespCode;
import com.ww.common.bean.UserInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Description - 共用基础Controller
 * @Author 查旺旺
 * @Date 2019/4/2 13:50
 */
public class BaseController {

	@Resource
	private Auth auth;

	/**
	 * 获取当前用户信息
	 * @return
     */
	protected UserInfo getUserInfo() {
		return getUserInfo(false);
	}

	protected UserInfo getUserInfo(boolean anonymousable) {
		return auth.getUserInfo(anonymousable);
	}

	protected static <T> MessageResp<T> success() {
		return buildResp(null, new RespCode("100200", "成功"));
	}

	protected static <T> MessageResp<T> success(T data) {
		return buildResp(data, new RespCode("100200", "成功"));
	}

	protected static <T> MessageResp<T> buildResp(T data, RespCode respEnum) {
		MessageResp<T> resp = new MessageResp<T>();
		resp.setMessage(respEnum);
		resp.setData(data);
		return resp;
	}


}
