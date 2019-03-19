package com.zsw.design.factory.factorymethod;

import com.zsw.design.factory.Pizza;
import com.zsw.design.factory.PizzaFactory;
import com.zsw.design.factory.simplefactory.usual.CheesePizza;
import com.zsw.design.factory.simplefactory.usual.ClamPizza;
import com.zsw.design.factory.simplefactory.usual.PepperoniPizza;
import com.zsw.design.factory.simplefactory.usual.VeggiePizza;
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
