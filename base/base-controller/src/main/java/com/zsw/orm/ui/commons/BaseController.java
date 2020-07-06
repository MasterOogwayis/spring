package com.zsw.orm.ui.commons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Collection;
import java.util.stream.Collectors;


/**
 * @author ZhangShaowei on 2017/5/9 10:32
 */

public class BaseController {

    /**
     *
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     *
     */
    protected Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    /**
     * @param ex exception
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    protected Object exceptionHandler(Exception ex) {
        ex.printStackTrace();
        return ex.getMessage();
    }



//    protected Principal getUserInfo() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication();
//        if (principal instanceof OAuth2Authentication) {
//            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
//            UserInfo userInfo = new UserInfo();
//            userInfo.setUsername(oAuth2Authentication.getName());
//            Collection<GrantedAuthority> collection = oAuth2Authentication.getAuthorities();
//            userInfo.setRoles(collection.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
//            return userInfo;
//        }
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    protected ApiResult exceptionHandler(MethodArgumentNotValidException ex) {
//        ex.printStackTrace();
//        // 参数验证失败
//        String message = ex.getBindingResult().getAllErrors()
//                .stream()
//                .map(FieldError.class::cast)
//                .map(fieldError ->
//                        fieldError.getField() + "=" +
//                                fieldError.getRejectedValue() + ", error=" +
//                                fieldError.getDefaultMessage()
//                ).collect(Collectors.joining("。"));
//        ApiResult<?> fail = ApiResult.create(EpcResultType.PARAMS_ERROR);
//        fail.setMessage(message);
//        return fail;
//    }

}
