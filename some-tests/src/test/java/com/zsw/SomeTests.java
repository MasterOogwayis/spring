package com.zsw;

import com.foo.demo.IShout;

import java.util.ServiceLoader;

/**
 * @author Shaowei Zhang on 2022/2/14 22:14
 */
public class SomeTests {

    public static void main(String[] args) throws Exception {

        ServiceLoader<IShout> load = ServiceLoader.load(IShout.class);

        load.forEach(IShout::shout);


    }

}
