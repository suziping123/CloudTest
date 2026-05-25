package org.cloud.order.exception;

import org.cloud.model.common.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author SuZiPing
 * @version 1.0
 */
//@ResponseBody
//@ControllerAdvice

@RestControllerAdvice // 全局异常处理器，等同于 @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public R error(Exception e) {
        e.printStackTrace();
        return R.error("服务器异常", null, 500);
    }
}
