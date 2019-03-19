package com.zsw.demo.pattern.factory.abstractfactory.ny;

import com.zsw.demo.pattern.factory.Pizza;
import lombok.Data;

import java.util.Collections;

/**
 * @author Shaowei Zhang on 2019/3/7 20:42
 **/
@Data
public class NYClamPizza extends Pizza {


    public NYClamPizza() {
        super("NewYork clam Pizza", "simple", "simple", Collections.singletonList("蛤"));
    }

}
