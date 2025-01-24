package com.lmj.estate.domain.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * @author lmj
 * @version 1.0
 * @description 全局异常处理
 * @date 2025/01/24 15:35:06
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {
    @ExceptionHandler(value = {Exception.class})
    public R<Void> ex(Exception e)
    {
        log.debug(e.getMessage());
        return R.no(e.getMessage());
    }

}
