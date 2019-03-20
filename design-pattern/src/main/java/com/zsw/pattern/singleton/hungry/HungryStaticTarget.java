package com.zsw.pattern.singleton.hungry;

/**
 * 和HungryTarget 相同
 *
 * @author ZhangShaowei on 2019/3/19 14:20
 **/
public class HungryStaticTarget {

    private HungryStaticTarget() {
    }

    public static final HungryStaticTarget INSTANCE;

    static {
        INSTANCE = new HungryStaticTarget();
    }


    public static HungryStaticTarget getInstance() {
        return INSTANCE;
    }


}
