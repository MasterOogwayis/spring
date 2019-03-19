package com.zsw.demo.pattern.factory.abstractfactory;

import com.zsw.demo.pattern.factory.PizzaFactory;
import com.zsw.demo.pattern.factory.PizzaStore;
import com.zsw.demo.pattern.factory.Pizza;
import com.zsw.demo.pattern.factory.factorymethod.NYPizzaFactory;
import lombok.Data;

/**
 * @author Shaowei Zhang on 2019/3/7 21:18
 **/
@Data
public class NYStylePizzaStore extends PizzaStore {

    private PizzaFactory pizzaFactory = new NYPizzaFactory();

    @Override
    public Pizza createPizza(String type) {
        return this.pizzaFactory.createPizza(type);
    }
}
