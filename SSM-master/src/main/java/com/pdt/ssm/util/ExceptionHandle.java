package com.pdt.ssm.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Map<String, Object> validatorErrorHandler(BindException bindException) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>(2);
        List<FieldError> errors = bindException.getBindingResult().getFieldErrors();

        List message = new ArrayList();
        for (int i = 0, size = errors.size(); i < size; i++) {
            FieldError fieldError = errors.get(i);
            message.add(fieldError.getDefaultMessage());
        }
        map.put("message", StringUtils.join(message.toArray(), ","));
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
