package com.demo.spring.data.binding;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangShaowei on 2021/7/7 9:42
 */
@Slf4j
public class DataBindingDemo {

    public static void main(String[] args) {
        //空白对象
        User user = new User();

        //
        DataBinder binder = new DataBinder(user, "");
        // 1. 调整 IgnoreUnknownFields true（默认） -> false（抛出异常，age 字段不存在于 User 类）
//        binder.setIgnoreUnknownFields(false);
        // 2. 调整自动增加嵌套路径 true（默认） —> false
        binder.setIgnoreInvalidFields(true);

        // 3. 调整 ignoreInvalidFields false(默认） -> true（默认情况调整不变化，需要调增 AutoGrowNestedPaths 为 false）
        binder.setIgnoreInvalidFields(true);

        // 4. 必要属性
        binder.setRequiredFields("id", "name", "address");

        Map<String, Object> source = new HashMap<>();
        source.put("id", 1L);
        source.put("name", "zsw");

        source.put("age", 18);

        source.put("company", new Company());
        source.put("company.name", "烟草");

        PropertyValues propertyValues = new MutablePropertyValues(source);
        binder.bind(propertyValues);

        log.info("User: {}", user);
        log.info("Binding result: {}", binder.getBindingResult());

    }

    @Data
    static class User {
        private Long id;
        private String name;
        //        private Integer age;
        private String address;

        private Company company;
    }

    @Data
    static class Company {
        private String name;
    }


}
