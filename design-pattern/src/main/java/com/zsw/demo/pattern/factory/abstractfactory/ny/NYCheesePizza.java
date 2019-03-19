package com.zsw.demo.pattern.factory.abstractfactory.ny;

import com.zsw.demo.pattern.factory.Pizza;
import lombok.Data;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:40
 **/
@Data
public class NYCheesePizza extends Pizza {

    public NYCheesePizza() {
        super("NewYork Cheese Pizza", "simple", "simple", Collections.singletonList("奶酪"));
    }

}
