package com.zsw.design.factory.factorymethod;

import com.zsw.design.factory.Pizza;
import com.zsw.design.factory.PizzaFactory;
import com.zsw.design.factory.simplefactory.ny.NYCheesePizza;
import com.zsw.design.factory.simplefactory.ny.NYClamPizza;
import com.zsw.design.factory.simplefactory.ny.NYPepperoniPizza;
import com.zsw.design.factory.simplefactory.ny.NYVeggiePizza;
import lombok.SneakyThrows;

import java.util.Objects;

/**
 * @author Shaowei Zhang on 2019/3/7 20:37
 **/
public class NYPizzaFactory implements PizzaFactory {


    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if ("cheese".equals(type)) {
            pizza = new NYCheesePizza();
        } else if ("pepperoni".equals(type)) {
            pizza = new NYPepperoniPizza();
        } else if ("clam".equals(type)) {
            pizza = new NYClamPizza();
        } else if ("veggie".equals(type)) {
            pizza = new NYVeggiePizza();
        }
        return pizza;
    }

    @SneakyThrows
    public <T extends Pizza> T createPizza(Class<T> clazz) {
        if (Objects.nonNull(clazz)) {
            return clazz.newInstance();
        }
        return null;


    }


}
