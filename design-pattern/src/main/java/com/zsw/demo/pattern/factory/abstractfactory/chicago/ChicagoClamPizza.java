package com.zsw.demo.pattern.factory.abstractfactory.chicago;

import com.zsw.demo.pattern.factory.Pizza;
import lombok.Data;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:42
 **/
@Data
public class ChicagoClamPizza extends Pizza {

    public ChicagoClamPizza() {
        super("Chicago clam Pizza", "simple", "simple", Collections.singletonList("蛤"));
    }

}
