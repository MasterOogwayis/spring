package com.zsw.base.api.commons;

import ch.qos.logback.classic.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zsw.base.client.ResultData;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author ZhangShaowei on 2017/5/9 10:32
 */
public class BaseApiController {

    /**
     *
     */
    protected final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());


    /**
     *
     */
    protected final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    /**
     * @param ex exception
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    protected Object exceptionHandler(Exception ex) {
        if (logger.isDebugEnabled()) {
            ex.printStackTrace();
        }
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) ex;
            List<ObjectError> list = me.getBindingResult().getAllErrors();
            List<String> data = list.stream()
                    .map(FieldError.class::cast)
                    .map(fieldError -> fieldError.getField() + "=" + fieldError.getRejectedValue()
                            + ", error=" + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResultData.failure(data.toString());
        } else if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException ce = (ConstraintViolationException) ex;
            Set<ConstraintViolation<?>> set = ce.getConstraintViolations();
            List<String> list = set.stream()
                    .map(constraintViolation -> {
                        PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
                        return pathImpl.getLeafNode().getName() + "=" + pathImpl.getLeafNode().getValue()
                                + ", error=" + constraintViolation.getMessage();
                    })
                    .collect(Collectors.toList());
            return list;
        }
        return ex;
    }

}
