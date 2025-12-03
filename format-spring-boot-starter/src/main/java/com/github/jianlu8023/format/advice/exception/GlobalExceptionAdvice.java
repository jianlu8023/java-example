package com.github.jianlu8023.format.advice.exception;

import com.github.jianlu8023.format.response.ResponseStatus;
import com.github.jianlu8023.format.response.*;
import lombok.extern.slf4j.*;
import org.springframework.validation.*;
import org.springframework.web.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.*;
import org.springframework.web.servlet.*;

import java.nio.file.*;
import java.util.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

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
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            log.error("请求参数类型错误：", e);
            MethodArgumentTypeMismatchException ex = (MethodArgumentTypeMismatchException) e;
            return ApiResponse.error(ResponseStatus.BAD_REQUEST.getCode(), "请求参数类型不正确：" + ex.getName());
        } else if (e instanceof NoHandlerFoundException) {
            NoHandlerFoundException ex = (NoHandlerFoundException) e;
            log.error("请求地址不存在：", e);
            return ApiResponse.error(ResponseStatus.METHOD_IMPLEMENTED, ex.getRequestURL());
        } else if (e instanceof AccessDeniedException) {
            AccessDeniedException ex = (AccessDeniedException) e;
            log.error("权限不足：", e);
            return ApiResponse.error(ResponseStatus.INVALID_TOKEN.getCode(), ex.getMessage());
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
     * 系统异常处理
     */
    @ExceptionHandler(Throwable.class)
    public ApiResponse<String> exception(Throwable throwable) {
        log.error("系统异常", throwable);
        return ApiResponse.error(ResponseStatus.INTERNAL_SERVER_ERROR);
    }
}
