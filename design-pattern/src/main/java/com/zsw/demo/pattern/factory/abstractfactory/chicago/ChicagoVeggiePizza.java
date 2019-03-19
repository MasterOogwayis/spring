package com.zsw.demo.pattern.factory.abstractfactory.chicago;

import com.zsw.demo.pattern.factory.Pizza;
import lombok.Data;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:43
 **/
@Data
public class ChicagoVeggiePizza extends Pizza {

    public ChicagoVeggiePizza() {
        super("Chicago Veggie Pizza", "dougn", "dougn", Collections.singletonList("蔬菜"));
    }
}
