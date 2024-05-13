package com.example.lotspring.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseVO<Object> exceptionHandler(Exception e, HttpServletRequest httpServerRequest) {

        String url = httpServerRequest.getRequestURI();

        log.error("httpServerRequest url :{{}}", url);

        // 处理业务异常
        if (e instanceof BizException bizException) {

            if (bizException.getCode() == null) {

                bizException.setCode(ResponseStatus.BAD_REQUEST.getCode());
            }
            return ResponseVO.failure(bizException.getCode(), bizException.getMessage());
        } else if (e instanceof MethodArgumentNotValidException methodArgumentNotValidException) {

            // 参数检验异常
            Map<String, String> map = new HashMap<>();
            BindingResult result = methodArgumentNotValidException.getBindingResult();
            result.getFieldErrors().forEach(item -> {

                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put(field, message);
            });
            log.error("数据校验出现错误：", e);
            return ResponseVO.failure(ResponseStatus.BAD_REQUEST, map);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {

            log.error("请求方法错误：", e);
            return ResponseVO.failure(ResponseStatus.BAD_REQUEST.getCode(), "请求方法不正确");
        } else if (e instanceof MissingServletRequestParameterException ex) {

            log.error("请求参数缺失：", e);
            return ResponseVO.failure(ResponseStatus.BAD_REQUEST.getCode(), "请求参数缺少: " + ex.getParameterName());
        } else if (e instanceof MethodArgumentTypeMismatchException ex) {

            log.error("请求参数类型错误：", e);
            return ResponseVO.failure(ResponseStatus.BAD_REQUEST.getCode(), "请求参数类型不正确：" + ex.getName());
        } else if (e instanceof NoHandlerFoundException ex) {

            log.error("请求地址不存在：", e);
            return ResponseVO.failure(ResponseStatus.NOT_EXIST, ex.getRequestURL());
        } else if (e instanceof AccessDeniedException ex) {

            return ResponseVO.failure(ResponseStatus.UNAUTHORIZED, ex.getMessage());
        } else {

            //如果是系统的异常，比如空指针这些异常
            log.error("【系统异常】", e);
            return ResponseVO.failure(ResponseStatus.SYSTEM_ERROR.getCode(), ResponseStatus.SYSTEM_ERROR.getMessage());
        }
    }

}
