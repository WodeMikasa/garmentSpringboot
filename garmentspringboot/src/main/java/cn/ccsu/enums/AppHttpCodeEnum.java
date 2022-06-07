package cn.ccsu.enums;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现了错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"), EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    RETURN_ERROR(506,"借阅者和登录用户不一致"),
    PASSWORD_ERROR(507,"新密码与旧密码不能一样"),
    OUTSTORE_ERROR(509,"出库单新增失败"),
    OUTSTORE_ERROR2(510,"出库明细新增失败"),
    OUTSTORE_ERROR3(511,"服装库存数不足"),
    OUTSTORE_ERROR4(512,"建立出库单与明细对应关系出错"),
    PASSWORD_ERROR2(508,"密码输入错误");
    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
