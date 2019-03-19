package com.zsw.design.factory.simplefactory.usual;

import com.zsw.design.factory.Pizza;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:40
 **/
public class CheesePizza extends Pizza {

    public CheesePizza() {
        super("Cheese Pizza", "simple", "simple", Collections.singletonList("奶酪"));
    }

}
