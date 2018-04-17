package com.zsw.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User
 *
 * @author ZhangShaowei on 2018/4/13 14:54
 **/
public class User {

    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @Min(18)
    private Integer age;

    /**  */
    public String getName() {
        return name;
    }

    /**  */
    public void setName(String name) {
        this.name = name;
    }

    /**  */
    public Integer getAge() {
        return age;
    }

    /**  */
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
