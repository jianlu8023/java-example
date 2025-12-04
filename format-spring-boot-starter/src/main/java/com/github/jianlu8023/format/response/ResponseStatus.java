package com.github.jianlu8023.format.response;

public enum ResponseStatus {

    // 2xx 成功状态码
    SUCCESS(200, "操作成功"),
    CREATED(201, "创建成功"),
    ACCEPTED(202, "请求已接受"),
    NO_CONTENT(204, "无内容"),

    // 4xx 客户端错误状态码
    BAD_REQUEST(400, "请求错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不允许"),
    REQUEST_TIMEOUT(408, "请求超时"),
    CONFLICT(409, "请求冲突"),

    // 参数相关错误
    PARAM_ERROR(1000, "参数异常"),
    VALIDATION_FAILED(1001, "参数校验失败"),

    // 认证授权相关错误
    INVALID_TOKEN(2001, "访问令牌不合法"),
    ACCESS_DENIED(2002, "没有权限访问该资源"),
    ACCOUNT_DISABLED(2003, "账户已被禁用"),
    ACCOUNT_EXPIRED(2004, "账户已过期"),

    // 业务相关错误
    BUSINESS_ERROR(3000, "业务异常"),
    DATA_NOT_FOUND(3001, "数据不存在"),
    DATA_EXISTS(3002, "数据已存在"),

    // 5xx 服务器错误状态码
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    NOT_IMPLEMENTED(501, "功能未实现"),
    BAD_GATEWAY(502, "网关错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    GATEWAY_TIMEOUT(504, "网关超时");

    /**
     * 消息
     */
    private final String message;

    /**
     * 响应码
     */
    private final Integer code;

    ResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ResponseStatus{" +
                       "code=" + code +
                       ", message='" + message + '\'' +
                       '}';
    }
}