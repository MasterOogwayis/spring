package com.zsw.design.factory.simplefactory.ny;

import com.zsw.design.factory.Pizza;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:41
 **/
public class NYPepperoniPizza extends Pizza {

    public NYPepperoniPizza() {
        super("NewYork Pepperoni Pizza", "dougn", "dougn", Collections.singletonList("意大利辣味香肠"));
    }


}
