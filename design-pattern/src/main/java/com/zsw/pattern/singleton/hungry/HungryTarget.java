package com.zsw.pattern.singleton.hungry;

/**
 * @author ZhangShaowei on 2019/3/19 14:10
 **/
public class HungryTarget {

    private static final HungryTarget INSTANCE = new HungryTarget();

    private static HungryTarget getInstance() {
        return INSTANCE;
    }

}
