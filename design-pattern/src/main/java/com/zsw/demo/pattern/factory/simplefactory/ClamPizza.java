package com.zsw.demo.pattern.factory.simplefactory;

import com.zsw.demo.pattern.factory.Pizza;
import lombok.Data;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:42
 **/
@Data
public class ClamPizza extends Pizza {

    public ClamPizza() {
        super("Cheese Pizza", "simple", "simple", Collections.singletonList("蛤"));
    }
}
