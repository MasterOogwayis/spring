package com.zsw.demo.pattern.factory.simplefactory;

import com.zsw.demo.pattern.factory.Pizza;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author Shaowei Zhang on 2019/3/7 20:41
 **/
@Data
public class PepperoniPizza extends Pizza {

    public PepperoniPizza() {
        super("Pepperoni Pizza", "dougn", "dougn", Collections.singletonList("意大利辣味香肠"));
    }
}
