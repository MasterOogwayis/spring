package com.zsw.demo.pattern.factory.factorymethod;

import com.zsw.demo.pattern.factory.Pizza;
import com.zsw.demo.pattern.factory.PizzaFactory;
import com.zsw.demo.pattern.factory.simplefactory.CheesePizza;
import com.zsw.demo.pattern.factory.simplefactory.ClamPizza;
import com.zsw.demo.pattern.factory.simplefactory.PepperoniPizza;
import com.zsw.demo.pattern.factory.simplefactory.VeggiePizza;
import lombok.SneakyThrows;

import java.util.Objects;

/**
 * @author Shaowei Zhang on 2019/3/7 20:37
 **/
public class SimplePizzaFactory implements PizzaFactory {


    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if ("cheese".equals(type)) {
            pizza = new CheesePizza();
        } else if ("pepperoni".equals(type)) {
            pizza = new PepperoniPizza();
        } else if ("clam".equals(type)) {
            pizza = new ClamPizza();

        } else if ("veggie".equals(type)) {
            pizza = new VeggiePizza();
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
