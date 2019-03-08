package com.yezi.luframe.advice;

import com.yezi.luframe.enums.CodeEnum;
import com.yezi.luframe.vo.ArgumentInvalidResult;
import com.yezi.luframe.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 统一异常处理
 *
 * @author yezi
 * @date 2019/3/4 10:59
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object methodArgumentNotValidHandler(HttpServletRequest request,
                                                Throwable throwable) {
        log.info("HttpServletRequest URL={}", request.getRequestURI());
        log.error("", throwable);
        //POST请求参数异常处理
        if (throwable instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) throwable;
            //按需重新封装需要返回的错误信息
            //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
            for (FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
                ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
                invalidArgument.setDefaultMessage(error.getDefaultMessage());
                invalidArgument.setField(error.getField());
                invalidArgument.setRejectedValue(error.getRejectedValue());
                log.info("参数异常:" + error.getField() + error.getDefaultMessage());
                return new JsonResult(CodeEnum.ERROR_PARAM.getCode(), error.getDefaultMessage(), null);
            }
            return new JsonResult(CodeEnum.ERROR_PARAM.getCode(), CodeEnum.ERROR_PARAM.getMessage(), null);
        }
        //GET请求参数缺失处理
        if (throwable instanceof MissingServletRequestParameterException) {
            return new JsonResult(CodeEnum.ERROR_PARAM.getCode(),
                    "参数异常:" + ((MissingServletRequestParameterException) throwable).getParameterName() + "不能为空！", null);
        }
        //GET请求参数校验
        if (throwable instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) throwable).getConstraintViolations();
            StringBuilder strBuilder = new StringBuilder();
            for (ConstraintViolation<?> violation : violations) {
                strBuilder.append(violation.getInvalidValue() + ": " + violation.getMessage() + "\n");
            }
            String result = strBuilder.toString();
            return new JsonResult(CodeEnum.ERROR_PARAM.getCode(), "参数异常: " + result, null);
        }
        return new JsonResult(CodeEnum.ERROR.getCode(), CodeEnum.ERROR.getMessage(), null);

    }
}
