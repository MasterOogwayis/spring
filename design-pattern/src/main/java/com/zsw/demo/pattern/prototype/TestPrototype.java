package com.zsw.demo.pattern.prototype;

import lombok.SneakyThrows;

/**
 * @author Shaowei Zhang on 2019/3/9 19:42
 **/
public class TestPrototype {

    @SneakyThrows
    public static void main(String[] args) {

        PrototypeTarget target = new PrototypeTarget();
        target.setName("zsw");

        System.out.println(target);

        PrototypeTarget prototypeTarget = (PrototypeTarget) target.clone();
        System.err.println(prototypeTarget);

        System.out.println(target.equals(prototypeTarget));



    }

}
