package com.demo.factories;

import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

/**
 * @author Administrator on 2019/9/8 14:43
 **/
public class TestSpringFactory {

    public static void main(String[] args) {

        List<DoSomething> doSomethings = SpringFactoriesLoader.loadFactories(DoSomething.class, TestSpringFactory.class.getClassLoader());
        doSomethings.forEach(DoSomething::execute);

    }

}
