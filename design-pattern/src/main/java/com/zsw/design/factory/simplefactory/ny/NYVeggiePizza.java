package com.zsw.design.factory.simplefactory.ny;

import com.zsw.design.factory.Pizza;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:43
 **/
public class NYVeggiePizza extends Pizza {

    public NYVeggiePizza() {
        super("NewYork Veggie Pizza", "dougn", "dougn", Collections.singletonList("蔬菜"));
    }
}
