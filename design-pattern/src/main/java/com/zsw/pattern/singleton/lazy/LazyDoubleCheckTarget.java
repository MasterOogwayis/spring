package com.zsw.pattern.singleton.lazy;

import com.zsw.pattern.DeepCloneable;

import java.util.Objects;

/**
 * 懒加载 双检索
 *
 *
 * @author ZhangShaowei on 2019/3/19 14:22
 **/
public class LazyDoubleCheckTarget extends DeepCloneable<LazyDoubleCheckTarget> {

    private static final long serialVersionUID = -6495131582642946643L;

    private LazyDoubleCheckTarget() {
    }

    private static LazyDoubleCheckTarget INSTANCE;


//    public static LazyTarget getInstance() {
//        if (Objects.isNull(INSTANCE)) {
//            INSTANCE = new LazyTarget();
//        }
//        return INSTANCE;
//    }


    public static LazyDoubleCheckTarget getInstance() {
        if (Objects.isNull(INSTANCE)) {
            synchronized (LazyDoubleCheckTarget.class) {
                if (Objects.isNull(INSTANCE)) {
                    INSTANCE = new LazyDoubleCheckTarget();
                }
            }
        }
        return INSTANCE;
    }

}
