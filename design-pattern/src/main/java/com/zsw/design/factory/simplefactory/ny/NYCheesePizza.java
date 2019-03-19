package com.zsw.design.factory.simplefactory.ny;

import com.zsw.design.factory.Pizza;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:40
 **/
public class NYCheesePizza extends Pizza {

    public NYCheesePizza() {
        super("NewYork Cheese Pizza", "simple", "simple", Collections.singletonList("奶酪"));
    }

}
