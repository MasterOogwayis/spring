package com;

import java.util.List;

/**
 * @author ZhangShaowei on 2017/11/23 14:47
 */

public class Person {

    private Integer id;

    private String name;

    private String address;

    private Integer age;

    private List<String> roles;

    /**  */
    public Integer getId() {
        return id;
    }

    /**  */
    public void setId(Integer id) {
        this.id = id;
    }

    /**  */
    public String getName() {
        return name;
    }

    /**  */
    public void setName(String name) {
        this.name = name;
    }

    /**  */
    public String getAddress() {
        return address;
    }

    /**  */
    public void setAddress(String address) {
        this.address = address;
    }

    /**  */
    public Integer getAge() {
        return age;
    }

    /**  */
    public void setAge(Integer age) {
        this.age = age;
    }

    public Person(Integer id, String name, String address, Integer age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
    }

    /**  */
    public List<String> getRoles() {
        return roles;
    }

    /**  */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", roles=" + roles +
                '}';
    }

}
