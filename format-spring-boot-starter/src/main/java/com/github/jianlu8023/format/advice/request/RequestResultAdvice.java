package com.github.jianlu8023.format.advice.request;

import com.github.jianlu8023.format.annotation.*;
import lombok.extern.slf4j.*;
import org.springframework.core.*;
import org.springframework.core.annotation.*;
import org.springframework.http.converter.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.*;

import java.lang.annotation.*;
import java.lang.reflect.*;

@RestControllerAdvice
@Slf4j
public class RequestResultAdvice extends RequestBodyAdviceAdapter {

    private static final Class<? extends Annotation> ANNOTATION_TYPE = RequestCheck.class;

    @Override
    public boolean supports(MethodParameter methodParameter,
                            Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(methodParameter.getContainingClass(), ANNOTATION_TYPE) || methodParameter.hasMethodAnnotation(ANNOTATION_TYPE);
        // return false;
    }
}
