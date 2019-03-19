package com.zsw.demo.pattern.factory;

/**
 * 所有工厂都必须实现这个工厂
 *
 * @author Shaowei Zhang on 2019/3/7 21:24
 **/
public interface PizzaFactory {

    Pizza createPizza(String type);

    <T extends Pizza> T createPizza(Class<T> clazz);

}
