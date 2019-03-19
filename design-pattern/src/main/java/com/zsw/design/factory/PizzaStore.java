package com.zsw.design.factory;


/**
 * @author Shaowei Zhang on 2019/3/7 20:58
 **/
public abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza = this.createPizza(type);

        pizza.prepare();

        pizza.bake();

        pizza.cut();

        pizza.box();

        return pizza;
    }


    protected abstract Pizza createPizza(String type);

}
