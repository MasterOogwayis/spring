package com.zsw.design.factory.abstractfactory;

import com.zsw.design.factory.Pizza;
import com.zsw.design.factory.PizzaFactory;
import com.zsw.design.factory.PizzaStore;
import com.zsw.design.factory.factorymethod.SimplePizzaFactory;

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
