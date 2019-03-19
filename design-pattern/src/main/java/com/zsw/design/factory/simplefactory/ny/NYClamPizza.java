package com.zsw.design.factory.simplefactory.ny;

import com.zsw.design.factory.Pizza;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:42
 **/
public class NYClamPizza extends Pizza {


    public NYClamPizza() {
        super("NewYork clam Pizza", "simple", "simple", Collections.singletonList("蛤"));
    }

}
