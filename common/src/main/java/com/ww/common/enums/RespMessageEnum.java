package com.ww.common.enums;

import com.ww.common.aop.RespCode;

/**
 * 错误码
 * @author huxb
 * 2016年3月9日
 */
public class RespMessageEnum {
	
	/**
	 * 成功
	 */
	public final static RespCode SUCCESS = new RespCode("100200","成功");
	
	/**
	 * 一般性错误（1014**）
	 */
	public final static RespCode FAIL =  new RespCode("101400", "服务异常"),
		    ARGS_ERROR =  new RespCode("101401", "参数错误"),
		    FILE_UPLOAD =  new RespCode("101402", "文件上传错误"),
    		ERROR_SERVER =  new RespCode("101403", "系统错误"),
			ERROR_OPERATE =  new RespCode("101404", "操作错误"),
			ERROR_NETWORK = new RespCode("101405","网络异常"),
		    DB_ERROR =  new RespCode("101406","操作数据库异常"),

			//普通错误，不需要前段做特殊处理，仅需将提示内容展示给用户
			//请通过FideRException调用，勿直接引用
			SHOW_TO_USER = new RespCode("101407","普通错误");

	/**
	 * 定制错误消息
	 * @param msg 错误提示信息
	 * @return
	 */
	public static RespCode ERROR_MSG(String msg){
		return new RespCode("999999", msg);
	}
	
	/**
	 * app版本管理（1015**）
	 */
	public final static RespCode MUST_UPDATE = new RespCode("101501","强制更新！"),
			OPTION_UPDATE = new RespCode("101502","可选更新！");
	
	
	/**
	 * 用户相关（1016**）
	 */
	public final static RespCode SMS_ERROR = new RespCode("101601","验证码错误"),
			NO_USER_LOGIN = new RespCode("101602","登录用户不存在"),
			USER_ALREADY_EXISTS = new RespCode("101603","手机号码已经被注册"),
			TOKEN_NOT_EXIST = new RespCode("101604","登录失效，请重新登录"),
			OLD_PWD_ERROR = new RespCode("101605","旧密码错误"),
			ERROR_PWD = new RespCode("101606","用户名或密码不正确"),
			INFO_UPDATE_FAIL = new RespCode("101607","用户信息不能修改"),
			USER_UNIDENTIFIED = new RespCode("101608","用户未核身");
	
	
	/**
	 * 申请贷款（1017**）
	 */
	public final static RespCode LOAN_APPLY_FAIL = new RespCode("101701","申请贷款失败"),
			LOAN_UPDATE_FAIL = new RespCode("101702","贷款信息修改失败"),
			SIGNATURE_FAIL = new RespCode("101703","签约失败"),
			QUERYCMD_FAIL = new RespCode("101704","推荐码所属银行不支持该产品"),
			QUERY_NO_CMD_FAIL = new RespCode("101705","推荐码不存在");
	
	
	
	/**
	 * 数据源（1018**）
	 */
	public final static RespCode BIND_ACCOUNT_MYSELF_ERROR = new RespCode("101801","您已绑定此账号！"),
			BIND_ACCOUNT_EXIST_ERROR = new RespCode("101802","该账号已被绑定！"),
			DATAG_ALIWW_AUTH_FAIL = new RespCode("101803","阿里旺旺帐号认证失败"),
			DATAG_SHOP_WEBURL_EMPTY = new RespCode("101804","店铺网址为空"),
			DATAG_ALIWW_ALREADY_BIND = new RespCode("101805","该阿里旺旺号已绑定支付宝"),	
			DATAG_ALIPAY_ALREADY_BIND = new RespCode("101806","该支付宝帐号已绑店"),
			DATAG_ALIWW_HOME_CHANGE = new RespCode("101807","阿里旺旺认证失败：淘宝搜索服务改版"),
			DATAG_ALIWW_ACCT_NOT_EXIST = new RespCode("101808","阿里旺旺认证失败：阿里旺旺账号不存在"),
			DATAG_ALIWW_ACCTNAME_NOT_FULL = new RespCode("101809","阿里旺旺认证失败：阿里旺旺账号名称不全"),
			ERROR_ALIPAY_ACCOUNT_EMPTY = new RespCode("101810","支付宝不能为空"),
			ERROR_ALIPAYWW_EMPTY = new RespCode("101811","经营平台不能为空"),
			JSB_ALIPAY_ACT_FAIL = new RespCode("101812","支付宝绑定失败"),
			DATAG_ALIWW_BIND_EXIST = new RespCode("101813","该店已被他人关联使用，请联系客服解决！"),
			DATAG_LOGIN_ERROR=new RespCode("101814","请检查您的账号密码或店铺地址是否正确！"),
			TOBACCO_TITLE_EMPTY = new RespCode("101815","店铺名称为空"),
			ACC_TYPE_NOT_SUPPORTED = new RespCode("101816","账号类型错误"),
			ACCOUNT_NOT_EXIST = new RespCode("101817","账号不存在");
	
	
	/**
	 * DATAG回调存储文件（1019**）
	 */
	public final static RespCode DATAG_ACCOUNT_NO_EXIST = new RespCode("101901","资产账号不存在！"),
			DATAG_ACCOUNT_ARG_ERROR = new RespCode("101902","请求参数错误！");
	
	
	/**
	 * 在线支用还款信息（1021**）
	 */
	public final static RespCode FINANCE_NO_DRAWDOWN = new RespCode("102101","授信信息不存在！"),
			FINANCE_DRAWDOWN_AMOUNT_INSUFFICIENT = new RespCode("102102","剩余额度不足！"),
			PAYING = new RespCode("102103", "银行处理中");

	public final static RespCode ZN_ACCOUNT_EXIST = new RespCode("101001","存量用户！");

}


