package com.zsw.demo.pattern.factory;

import com.zsw.demo.pattern.factory.factorymethod.SimplePizzaFactory;

/**
 * @author Shaowei Zhang on 2019/3/7 20:35
 **/
public class SimpleFactoryTest {
    public static void main(String[] args) {
        SimplePizzaFactory factory = new SimplePizzaFactory();
        Pizza pizza = factory.createPizza("cheese");
        System.out.println(pizza);

    }


}
