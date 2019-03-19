package com.zsw.design.factory.simplefactory.chicago;

import com.zsw.design.factory.Pizza;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:43
 **/
public class ChicagoVeggiePizza extends Pizza {

    public ChicagoVeggiePizza() {
        super("Chicago Veggie Pizza", "dougn", "dougn", Collections.singletonList("蔬菜"));
    }
}
