package com.louis.springboot.demo.validator;

import org.apache.tomcat.util.buf.StringUtils;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public StringBuilder handle1(ConstraintViolationException ex){
        StringBuilder msg = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
            String paramName = pathImpl.getLeafNode().getName();
            String message = constraintViolation.getMessage();
            msg.append("[").append(message).append("]");
        }
        // 注意：Response类必须有get和set方法，不然会报错
        return msg;
    }


    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Map<String, Object> validatorErrorHandler(BindException bindException) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>(2);
        List<FieldError> errors = bindException.getBindingResult().getFieldErrors();;

        List message = new ArrayList();
        for (int i = 0, size = errors.size(); i < size; i++) {
            FieldError fieldError = errors.get(i);
            message.add(fieldError.getDefaultMessage());
        }
        map.put("message", StringUtils.join(message, ','));
        map.put("status", HttpServletResponse.SC_BAD_REQUEST);
        return map;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> ErrorHandler(Exception exception) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        map.put("message", exception.getMessage());
        return map;
    }
}