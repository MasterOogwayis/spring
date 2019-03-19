package com.zsw.demo.pattern.factory.simplefactory;

import com.zsw.demo.pattern.factory.Pizza;
import com.zsw.demo.pattern.factory.PizzaFactory;
import com.zsw.demo.pattern.factory.PizzaStore;
import com.zsw.demo.pattern.factory.factorymethod.SimplePizzaFactory;

/**
 * @author Shaowei Zhang on 2019/3/7 21:26
 **/
public class SimplePizzaStore extends PizzaStore {

    private PizzaFactory factory = new SimplePizzaFactory();

    @Override
    public Pizza createPizza(String type) {
        return this.factory.createPizza(type);
    }
}
