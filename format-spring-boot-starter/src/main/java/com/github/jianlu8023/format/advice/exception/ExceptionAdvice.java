package com.github.jianlu8023.format.advice.exception;

import com.github.jianlu8023.format.response.*;
import com.github.jianlu8023.format.response.ResponseStatus;
import lombok.extern.slf4j.*;
import org.springframework.http.converter.*;
import org.springframework.validation.*;
import org.springframework.web.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler({Exception.class})
    public ApiResponse<?> exception(Exception e) {
        if (e instanceof MethodArgumentNotValidException) {
            // 参数检验异常
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            Map<String, String> map = new HashMap<>();
            BindingResult result = methodArgumentNotValidException.getBindingResult();
            result.getFieldErrors().forEach((item) -> {
                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put(field, message);
            });
            log.error("参数检验异常 {} ", methodArgumentNotValidException.getMessage(), methodArgumentNotValidException);
            return ApiResponse.error(ResponseStatus.PARAM_ERROR, map);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException ex = (HttpRequestMethodNotSupportedException) e;
            log.error("请求方法错误：", ex);
            return ApiResponse.error(ResponseStatus.BAD_REQUEST.getCode(), String.format("请求方法不正确 %s 不支持", ex.getMethod()));
        } else if (e instanceof NoHandlerFoundException) {
            NoHandlerFoundException ex = (NoHandlerFoundException) e;
            log.error("请求地址不存在：", e);
            return ApiResponse.error(ResponseStatus.BUSINESS_ERROR, ex.getRequestURL());
        } else if (e instanceof HttpMessageNotReadableException) {
            HttpMessageNotReadableException ex = (HttpMessageNotReadableException) e;
            log.error("请求体不可读：", ex);
            return ApiResponse.error(ResponseStatus.BAD_REQUEST.getCode(), "请求数据格式不正确");
        } else {
            // 如果是系统的异常，比如空指针这些异常
            log.error("系统异常", e);
            return ApiResponse.error(ResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 参数缺失异常处理
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ApiResponse<String> badRequestException(MissingServletRequestParameterException ex) {
        String parameterName = ex.getParameterName();
        log.error("缺少必填参数 {}", ex.getMessage());
        return ApiResponse.error(ResponseStatus.BAD_REQUEST.getCode(), String.format("缺少必填参数: %s", parameterName));
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public ApiResponse<String> handleTypeMismatchException(NullPointerException ex) {
        log.error("空指针异常，{}", ex.getMessage());
        return ApiResponse.error("空指针异常");
    }

    /**
     * 请求参数绑定异常
     */
    @ExceptionHandler(BindException.class)
    public ApiResponse<Map<String, String>> bindException(BindException ex) {
        Map<String, String> map = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            map.put(error.getField(), error.getDefaultMessage());
        });
        log.error("参数绑定异常：", ex);
        return ApiResponse.error(ResponseStatus.PARAM_ERROR, map);
    }

    /**
     * 路径参数转换异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResponse<String> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error("路径参数转换异常：", ex);
        return ApiResponse.error(ResponseStatus.PARAM_ERROR.getCode(), "参数类型不匹配: " + ex.getName());
    }

    /**
     * 数组越界异常
     */
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ApiResponse<String> arrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException ex) {
        log.error("数组越界异常：", ex);
        return ApiResponse.error("数组越界异常");
    }

    /**
     * 系统异常处理
     */
    @ExceptionHandler(Throwable.class)
    public ApiResponse<String> throwableException(Throwable throwable) {
        log.error("系统异常", throwable);
        return ApiResponse.error(ResponseStatus.INTERNAL_SERVER_ERROR);
    }
}