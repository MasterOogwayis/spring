package com.zsw.design.factory.simplefactory.chicago;

import com.zsw.design.factory.Pizza;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:40
 **/
public class ChicagoCheesePizza extends Pizza {

    public ChicagoCheesePizza() {
        super("Chicago Cheese Pizza", "simple", "simple", Collections.singletonList("奶酪"));
    }

}
