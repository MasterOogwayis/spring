package com.zsw.demo.pattern.factory.abstractfactory.ny;

import com.zsw.demo.pattern.factory.Pizza;
import lombok.Data;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:41
 **/
@Data
public class NYPepperoniPizza extends Pizza {

    public NYPepperoniPizza() {
        super("NewYork Pepperoni Pizza", "dougn", "dougn", Collections.singletonList("意大利辣味香肠"));
    }


}
