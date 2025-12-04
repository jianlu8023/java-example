package com.github.jianlu8023.format.advice.response;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.github.jianlu8023.format.annotation.*;
import com.github.jianlu8023.format.response.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.*;
import org.springframework.core.annotation.*;
import org.springframework.http.*;
import org.springframework.http.converter.*;
import org.springframework.http.server.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.*;

import java.lang.annotation.*;

@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseCheck.class;
    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 检查类或方法上是否有 ResponseCheck 注解
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE) ||
                       returnType.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // 设置响应头
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // 获取方法返回类型
        Class<?> returnClass = returnType.getMethod().getReturnType();

        // 如果返回类型是 void 或 ResponseEntity，不进行封装
        if (returnClass.equals(void.class) || returnClass.equals(Void.class) ||
                    ResponseEntity.class.isAssignableFrom(returnClass)) {
            return body;
        }

        // 如果返回类型是 String，需要特殊处理
        if (body instanceof String || String.class.equals(returnClass)) {
            try {
                // 将 ApiResponse 转换为 JSON 字符串
                ApiResponse<?> apiResponse = ApiResponse.success(body);
                String value = objectMapper.writeValueAsString(apiResponse);

                // 设置HTTP状态码始终为200
                response.setStatusCode(HttpStatus.OK);
                return value;
            } catch (JsonProcessingException e) {
                throw new RuntimeException("JSON序列化失败", e);
            }
        }

        // 如果已经是 ApiResponse 类型，直接返回
        if (body instanceof ApiResponse) {
            ApiResponse<?> apiResponse = (ApiResponse<?>) body;
            // 设置HTTP状态码始终为200
            response.setStatusCode(HttpStatus.OK);
            return body;
        }

        // 其他情况统一包装成 ApiResponse
        ApiResponse<?> apiResponse = ApiResponse.success(body);
        // 设置HTTP状态码始终为200
        response.setStatusCode(HttpStatus.OK);
        return apiResponse;
    }
}