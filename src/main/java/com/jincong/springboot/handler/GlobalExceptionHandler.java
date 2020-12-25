package com.jincong.springboot.handler;

import cn.hutool.core.collection.CollectionUtil;
import com.jincong.springboot.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * 全局异常处理器
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/04/05
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 参数异常处理器
     *
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
     *
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
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResult handleUnexpectedServer(Exception ex) {
        LOGGER.error("系统异常：", ex);
        return new BaseResult("500", "系统发生异常，请联系管理员");
    }


    /**
     * 用来处理bean validation异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public  BaseResult resolveConstraintViolationException(ConstraintViolationException ex){
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if(CollectionUtil.isNotEmpty(constraintViolations)){
            StringBuilder msgBuilder = new StringBuilder();
            for(ConstraintViolation constraintViolation :constraintViolations){
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if(errorMessage.length()>1){
                errorMessage = errorMessage.substring(0,errorMessage.length()-1);
            }
            return new BaseResult(500, errorMessage);
        }

        return new BaseResult(500, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResult resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if(!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            return  new BaseResult(500, errorMessage);
        }
        return new BaseResult(500, ex.getMessage());
    }


}
