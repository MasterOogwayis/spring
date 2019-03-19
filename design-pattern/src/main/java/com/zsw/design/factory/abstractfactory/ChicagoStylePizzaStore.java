package com.zsw.design.factory.abstractfactory;

import com.zsw.design.factory.Pizza;
import com.zsw.design.factory.PizzaFactory;
import com.zsw.design.factory.PizzaStore;
import com.zsw.design.factory.factorymethod.ChicagoPizzaFactory;
import lombok.Data;

/**
 * @author Shaowei Zhang on 2019/3/7 21:18
 **/
public class ChicagoStylePizzaStore extends PizzaStore {

    PizzaFactory factory = new ChicagoPizzaFactory();

    @Override
    public Pizza createPizza(String type) {
        return this.factory.createPizza(type);
    }
}
