package com.zsw.demo.pattern.factory.simplefactory;

import com.zsw.demo.pattern.factory.Pizza;
import lombok.Data;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:43
 **/
@Data
public class VeggiePizza extends Pizza {

    public VeggiePizza() {
        super("Veggie Pizza", "dougn", "dougn", Collections.singletonList("蔬菜"));
    }
}
