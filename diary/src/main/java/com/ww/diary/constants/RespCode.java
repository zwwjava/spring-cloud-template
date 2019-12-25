package com.ww.diary.constants;


public enum RespCode {

    SUCCESS("100200","成功"),
    FAIL("101400", "服务异常"),
    ARGS_ERROR("101401", "参数错误"),
    FILE_UPLOAD("101402", "文件上传错误"),
    ERROR_SERVER("101403", "系统错误"),
    ERROR_OPERATE("101404", "操作错误"),
    ERROR_NETWORK ("101405","网络异常"),
    DB_ERROR("101406","操作数据库异常"),
    FORBIDDEN("101611", "无访问权限"),
    TOKEN_NOT_EXIST ("101604","登录失效或未登录，请重新登录"),
    SHOW_TO_USER("101407","普通错误");//普通错误，不需要前段做特殊处理，仅需将提示内容展示给用户

    private String code;
    private String msg;

    RespCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static RespCode customToUserMsg(String msg){
        RespCode respCode = RespCode.SHOW_TO_USER;
        respCode.setMsg(msg);
        return respCode;
    }

    public static RespCode customToUserMsgCode(String code, String msg){
        RespCode respCode = RespCode.SHOW_TO_USER;
        respCode.setCode(code);
        respCode.setMsg(msg);
        return respCode;
    }
    
}
