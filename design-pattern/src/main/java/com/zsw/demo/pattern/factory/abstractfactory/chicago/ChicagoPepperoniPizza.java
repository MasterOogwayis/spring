package com.zsw.demo.pattern.factory.abstractfactory.chicago;

import com.zsw.demo.pattern.factory.Pizza;
import lombok.Data;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:41
 **/
@Data
public class ChicagoPepperoniPizza extends Pizza {

    public ChicagoPepperoniPizza() {
        super("Chicago Pepperoni Pizza", "dougn", "dougn", Collections.singletonList("意大利辣味香肠"));
    }


}
