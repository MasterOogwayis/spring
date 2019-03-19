package com.zsw.design.factory.simplefactory.chicago;

import com.zsw.design.factory.Pizza;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:42
 **/
public class ChicagoClamPizza extends Pizza {

    public ChicagoClamPizza() {
        super("Chicago clam Pizza", "simple", "simple", Collections.singletonList("蛤"));
    }

}
