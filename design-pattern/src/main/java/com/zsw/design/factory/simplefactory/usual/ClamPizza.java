package com.zsw.design.factory.simplefactory.usual;

import com.zsw.design.factory.Pizza;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:42
 **/
public class ClamPizza extends Pizza {

    public ClamPizza() {
        super("Cheese Pizza", "simple", "simple", Collections.singletonList("蛤"));
    }
}
