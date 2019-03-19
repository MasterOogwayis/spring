package com.zsw.design.factory.abstractfactory;

import com.zsw.design.factory.Pizza;
import com.zsw.design.factory.PizzaFactory;
import com.zsw.design.factory.PizzaStore;
import com.zsw.design.factory.factorymethod.NYPizzaFactory;
import lombok.Data;

/**
 * @author Shaowei Zhang on 2019/3/7 21:18
 **/
public class NYStylePizzaStore extends PizzaStore {

    private PizzaFactory pizzaFactory = new NYPizzaFactory();

    @Override
    public Pizza createPizza(String type) {
        return this.pizzaFactory.createPizza(type);
    }
}
