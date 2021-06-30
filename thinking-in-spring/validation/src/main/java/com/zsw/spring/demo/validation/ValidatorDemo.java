package com.zsw.spring.demo.validation;

import com.demo.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.zsw.spring.demo.validation.ErrorsMessageDemo.createMessageSource;

/**
 * @author ZhangShaowei on 2021/6/30 13:34
 */
public class ValidatorDemo {

    public static void main(String[] args) {
        // 1. 创建 Validator
        Validator validator = new UserValidator();

        // 2. 判断是否支持目标类型
        User user = new User();
        System.out.println("user 对象是否被 UserValidator 支持检验：" + validator.supports(user.getClass()));

        // 3. 创建 Errors 对象
        Errors errors = new BeanPropertyBindingResult(user, "user");

        // 4. 获取 MessageSource
        MessageSource messageSource = createMessageSource();


    }


    static class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
//            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
//            String name = user.getName();
        }
    }

}
