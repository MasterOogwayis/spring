package com.zsw.demo.pattern.factory.factorymethod;

import com.zsw.demo.pattern.factory.Pizza;
import com.zsw.demo.pattern.factory.PizzaFactory;
import com.zsw.demo.pattern.factory.abstractfactory.ny.NYCheesePizza;
import com.zsw.demo.pattern.factory.abstractfactory.ny.NYClamPizza;
import com.zsw.demo.pattern.factory.abstractfactory.ny.NYPepperoniPizza;
import com.zsw.demo.pattern.factory.abstractfactory.ny.NYVeggiePizza;
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
