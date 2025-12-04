package com.github.jianlu8023.format.response;

import lombok.*;

import java.io.*;
import java.util.*;


@Getter
@Setter
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    private Integer code;

    private String message;

    private T data;

    private Boolean success;

    private ApiResponse() {
    }

    private ApiResponse(ResponseStatus status, T data, Boolean success) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = data;
        this.success = success;
    }


    private ApiResponse(Integer code, String message, T data, Boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    private ApiResponse(Integer code, String message, Boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(ResponseStatus.SUCCESS.getCode(), ResponseStatus.SUCCESS.getMessage(), true);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ResponseStatus.SUCCESS.getCode(), ResponseStatus.SUCCESS.getMessage(), data, true);
    }

    public static <T> ApiResponse<T> success(ResponseStatus status, T data) {
        return new ApiResponse<>(status, data, true);
    }

    // 添加新的成功响应方法，允许自定义消息
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(ResponseStatus.SUCCESS.getCode(), message, data, true);
    }

    public static <T> ApiResponse<T> success(Integer code, String message, T data) {
        return new ApiResponse<>(code, message, data, true);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(ResponseStatus.BUSINESS_ERROR.getCode(), message, false);
    }

    public static <T> ApiResponse<T> error(Integer code, String message) {
        return new ApiResponse<>(code, message, false);
    }

    public static <T> ApiResponse<T> error() {
        return new ApiResponse<>(ResponseStatus.INTERNAL_SERVER_ERROR.getCode(),
                ResponseStatus.INTERNAL_SERVER_ERROR.getMessage(), false);
    }

    public static <T> ApiResponse<T> error(ResponseStatus responseStatus) {
        return new ApiResponse<>(responseStatus.getCode(), responseStatus.getMessage(), false);
    }

    public static <T> ApiResponse<T> error(ResponseStatus responseStatus, T data) {
        return new ApiResponse<>(responseStatus.getCode(), responseStatus.getMessage(), data, false);
    }

    // 添加新的错误响应方法，允许自定义消息
    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>(ResponseStatus.BUSINESS_ERROR.getCode(), message, data, false);
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