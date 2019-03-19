package com.zsw.demo.pattern.factory.factorymethod;

import com.zsw.demo.pattern.factory.Pizza;
import com.zsw.demo.pattern.factory.PizzaFactory;
import com.zsw.demo.pattern.factory.abstractfactory.chicago.ChicagoCheesePizza;
import com.zsw.demo.pattern.factory.abstractfactory.chicago.ChicagoClamPizza;
import com.zsw.demo.pattern.factory.abstractfactory.chicago.ChicagoPepperoniPizza;
import com.zsw.demo.pattern.factory.abstractfactory.chicago.ChicagoVeggiePizza;
import lombok.SneakyThrows;

import java.util.Objects;

/**
 * @author Shaowei Zhang on 2019/3/7 20:37
 **/
public class ChicagoPizzaFactory implements PizzaFactory {


    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if ("cheese".equals(type)) {
            pizza = new ChicagoCheesePizza();
        } else if ("pepperoni".equals(type)) {
            pizza = new ChicagoPepperoniPizza();
        } else if ("clam".equals(type)) {
            pizza = new ChicagoClamPizza();
        } else if ("veggie".equals(type)) {
            pizza = new ChicagoVeggiePizza();
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
