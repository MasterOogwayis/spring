package com.zsw.demo.pattern.factory.abstractfactory.ny;

import com.zsw.demo.pattern.factory.Pizza;
import lombok.Data;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:43
 **/
@Data
public class NYVeggiePizza extends Pizza {

    public NYVeggiePizza() {
        super("NewYork Veggie Pizza", "dougn", "dougn", Collections.singletonList("蔬菜"));
    }
}
