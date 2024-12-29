package top.heyqing.heyaudio.entity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum REnum {

    /**
     * 正确无参数
     */
    SUCCESS(200, "success"),

    /**
     * 错误无参数
     */
    FAIL(500, "fail"),

    /**
     * token不合法
     */
    TOKEN_ILLEGAL(501, "token illegal"),

    /**
     * token过期
     */
    TOKEN_EXPIRE(502, "token expire"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIST(503, "user not exist"),

    /**
     * 用户不存在
     */
    USER_EXIST(504, "user exist"),

    /**
     * 账户或密码错误
     */
    ACCOUNT_OR_PASSWORD_INCORRECT(505, "account or password incorrect"),

    /**
     * email验证码错误
     */
    EMAIL_VERIFY_INCORRECT(506, "email verify incorrect");

    private final Integer code;
    private final String msg;
}
