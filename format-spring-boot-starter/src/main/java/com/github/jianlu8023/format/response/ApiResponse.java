package com.github.jianlu8023.format.response;

import java.io.*;
import java.util.*;


public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    private Integer code;

    private String message;

    private T data;

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

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(ResponseStatus.FAILURE.getCode(), message);
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


    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
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
