package com.github.jianlu8023.format.response;

import com.fasterxml.jackson.databind.*;
import org.springframework.http.*;

import javax.servlet.http.*;
import java.io.*;

public class ServletUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 设置响应状态码为200，并将业务状态码放在响应体中
     *
     * @param response    HttpServletResponse对象
     * @param apiResponse ApiResponse对象
     */
    public static void writeResponse(HttpServletResponse response, ApiResponse<?> apiResponse) throws IOException {
        // 设置HTTP状态码为200
        response.setStatus(HttpStatus.OK.value());

        // 设置响应内容类型
        response.setContentType("application/json;charset=UTF-8");

        // 将ApiResponse对象序列化为JSON字符串
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);

        // 写入响应数据
        PrintWriter writer = response.getWriter();
        writer.write(jsonResponse);
        writer.flush();
    }

    /**
     * 设置响应状态码为200，并将业务状态码放在响应体中
     *
     * @param response HttpServletResponse对象
     * @param json     JSON格式的响应数据
     */
    public static void writeJsonResponse(HttpServletResponse response, String json) throws IOException {
        // 设置HTTP状态码为200
        response.setStatus(HttpStatus.OK.value());

        // 设置响应内容类型
        response.setContentType("application/json;charset=UTF-8");

        // 写入响应数据
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
    }

    /**
     * 根据ApiResponse中的code设置HTTP状态码
     *
     * @param response    HttpServletResponse对象
     * @param apiResponse ApiResponse对象
     */
    public static void setHttpStatusByApiResponse(HttpServletResponse response, ApiResponse<?> apiResponse) {
        Integer businessCode = apiResponse.getCode();
        if (businessCode != null) {
            // 根据业务状态码映射HTTP状态码
            HttpStatus httpStatus = mapBusinessCodeToHttpStatus(businessCode);
            response.setStatus(httpStatus.value());
        }
    }

    /**
     * 将业务状态码映射为HTTP状态码
     *
     * @param businessCode 业务状态码
     *
     * @return 对应的HTTP状态码
     */
    private static HttpStatus mapBusinessCodeToHttpStatus(Integer businessCode) {
        // 2xx 成功状态码
        if (businessCode >= 200 && businessCode < 300) {
            switch (businessCode) {
                case 200:
                    return HttpStatus.OK;
                case 201:
                    return HttpStatus.CREATED;
                case 202:
                    return HttpStatus.ACCEPTED;
                case 204:
                    return HttpStatus.NO_CONTENT;
                default:
                    return HttpStatus.OK;
            }
        }
        // 4xx 客户端错误状态码
        else if (businessCode >= 400 && businessCode < 500) {
            switch (businessCode) {
                case 400:
                    return HttpStatus.BAD_REQUEST;
                case 401:
                    return HttpStatus.UNAUTHORIZED;
                case 403:
                    return HttpStatus.FORBIDDEN;
                case 404:
                    return HttpStatus.NOT_FOUND;
                case 405:
                    return HttpStatus.METHOD_NOT_ALLOWED;
                case 408:
                    return HttpStatus.REQUEST_TIMEOUT;
                case 409:
                    return HttpStatus.CONFLICT;
                default:
                    return HttpStatus.BAD_REQUEST;
            }
        }
        // 5xx 服务器错误状态码
        else if (businessCode >= 500 && businessCode < 600) {
            switch (businessCode) {
                case 500:
                    return HttpStatus.INTERNAL_SERVER_ERROR;
                case 501:
                    return HttpStatus.NOT_IMPLEMENTED;
                case 502:
                    return HttpStatus.BAD_GATEWAY;
                case 503:
                    return HttpStatus.SERVICE_UNAVAILABLE;
                case 504:
                    return HttpStatus.GATEWAY_TIMEOUT;
                default:
                    return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
        // 默认返回OK
        else {
            return HttpStatus.OK;
        }
    }
}