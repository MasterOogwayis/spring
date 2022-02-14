package com.zsw.entries;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Shaowei Zhang on 2022/2/14 23:14
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SingletonTarget implements Serializable {

    private static final long serialVersionUID = 2531731025831888278L;

    private static class TargetHandler {
        private static final SingletonTarget INSTANCE = new SingletonTarget();
    }


    public static SingletonTarget getInstance() {
        return TargetHandler.INSTANCE;
    }

    public Object readResolve() {
        return getInstance();
    }

}
