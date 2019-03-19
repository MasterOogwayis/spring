package com.zsw.design.factory;

import com.zsw.design.factory.factorymethod.ChicagoPizzaFactory;
import com.zsw.design.factory.factorymethod.NYPizzaFactory;
import com.zsw.design.factory.factorymethod.SimplePizzaFactory;

/**
 * @author Shaowei Zhang on 2019/3/7 21:37
 **/
public class FactoryMethodTest {

    public static void main(String[] args) {

        PizzaFactory pizzaFactory = new SimplePizzaFactory();
        Pizza pizza = pizzaFactory.createPizza("cheese");
        System.out.println(pizza);

        pizzaFactory = new NYPizzaFactory();
        pizza = pizzaFactory.createPizza("cheese");
        System.out.println(pizza);

        pizzaFactory = new ChicagoPizzaFactory();
        pizza = pizzaFactory.createPizza("cheese");
        System.out.println(pizza);


    }

}
