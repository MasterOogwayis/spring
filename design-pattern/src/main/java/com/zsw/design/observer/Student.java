package com.zsw.design.observer;

import lombok.AllArgsConstructor;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Shaowei Zhang on 2019/3/17 22:06
 **/
@AllArgsConstructor
public class Student implements Observer {

    private String name;

    @Override
    public void update(Observable o, Object arg) {
        Teacher teacher = (Teacher) o;
        System.out.println("学生 " + this.name + ": " + teacher.getName() + "布置了 " + arg + " 作业");
    }
}
