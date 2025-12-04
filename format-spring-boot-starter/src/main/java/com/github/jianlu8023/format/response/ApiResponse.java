package com.github.jianlu8023.format.response;

import java.io.*;
import java.util.*;


public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    private Integer code;

    private String message;

    private T data;

    public ApiResponse() {
    }

    public ApiResponse(ResponseStatus responseStatus) {
        this.code = responseStatus.getCode();
        this.message = responseStatus.getMessage();
    }

    public ApiResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(Integer code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    public ApiResponse(ResponseStatus status, T data) {
        this(status);
        this.data = data;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(ResponseStatus.SUCCESS.getCode(), ResponseStatus.SUCCESS.getMessage());
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ResponseStatus.SUCCESS.getCode(), ResponseStatus.SUCCESS.getMessage(), data);
    }

    public static <T> ApiResponse<T> success(ResponseStatus status, T data) {
        return new ApiResponse<>(status, data);
    }

    // 添加新的成功响应方法，允许自定义消息
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(ResponseStatus.SUCCESS.getCode(), message, data);
    }

    public static <T> ApiResponse<T> success(Integer code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(ResponseStatus.BUSINESS_ERROR.getCode(), message);
    }

    public static <T> ApiResponse<T> error(Integer code, String message) {
        return new ApiResponse<>(code, message);
    }

    public static <T> ApiResponse<T> error() {
        return new ApiResponse<>(ResponseStatus.INTERNAL_SERVER_ERROR.getCode(),
                ResponseStatus.INTERNAL_SERVER_ERROR.getMessage());
    }

    public static <T> ApiResponse<T> error(ResponseStatus responseStatus) {
        return new ApiResponse<>(responseStatus.getCode(), responseStatus.getMessage());
    }

    public static <T> ApiResponse<T> error(ResponseStatus responseStatus, T data) {
        return new ApiResponse<>(responseStatus.getCode(), responseStatus.getMessage(), data);
    }

    // 添加新的错误响应方法，允许自定义消息
    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>(ResponseStatus.BUSINESS_ERROR.getCode(), message, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiResponse<?> that = (ApiResponse<?>) o;
        return Objects.equals(code, that.code) && Objects.equals(message, that.message) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, data);
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                       "code=" + code +
                       ", message='" + message + '\'' +
                       ", data=" + data +
                       '}';
    }
}