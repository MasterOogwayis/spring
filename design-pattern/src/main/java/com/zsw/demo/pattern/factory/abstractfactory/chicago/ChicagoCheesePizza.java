package com.zsw.demo.pattern.factory.abstractfactory.chicago;

import com.zsw.demo.pattern.factory.Pizza;
import lombok.Data;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:40
 **/
@Data
public class ChicagoCheesePizza extends Pizza {

    public ChicagoCheesePizza() {
        super("Chicago Cheese Pizza", "simple", "simple", Collections.singletonList("奶酪"));
    }

}
