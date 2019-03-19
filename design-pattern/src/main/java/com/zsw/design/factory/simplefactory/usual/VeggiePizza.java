package com.zsw.design.factory.simplefactory.usual;

import com.zsw.design.factory.Pizza;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:43
 **/
public class VeggiePizza extends Pizza {

    public VeggiePizza() {
        super("Veggie Pizza", "dougn", "dougn", Collections.singletonList("蔬菜"));
    }
}
