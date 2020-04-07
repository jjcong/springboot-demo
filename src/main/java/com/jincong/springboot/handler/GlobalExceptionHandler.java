package com.jincong.springboot.handler;

import com.jincong.springboot.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理器
 * 
 * @author  j_cong
 * @date    2020/04/05
 * @version V1.0
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 参数异常处理器
     * @param ex
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BaseResult handleHttpMessageNotReabledException(MissingServletRequestParameterException ex) {
        LOGGER.error("缺少请求参数， {}", ex.getMessage());
        return new BaseResult("400", "缺少必须的参数！");
    }

    /**
     * 空指针异常处理器
     * @param ex NullPointerException
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResult handleTypeMismatchException(NullPointerException ex) {
        LOGGER.error("空指针异常，{}", ex.getMessage());
        return new BaseResult("500", "空指针异常!");
    }


    /**
     * 系统异常 预期以外异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResult handleUnexpectedServer(Exception ex) {
        LOGGER.error("系统异常：", ex);
        return new BaseResult("500", "系统发生异常，请联系管理员");
    }


}
