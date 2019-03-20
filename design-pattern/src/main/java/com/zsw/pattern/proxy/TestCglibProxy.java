package com.zsw.pattern.proxy;

import com.zsw.pattern.proxy.cglib.CglibProxy;
import com.zsw.pattern.proxy.target.Worker;
import net.sf.cglib.core.DebuggingClassWriter;

/**
 * @author ZhangShaowei on 2019/3/20 9:59
 **/
public class TestCglibProxy {

    public static void main(String[] args) {

        // 输出动态生成的文件
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "cglib");

        Worker instance = new CglibProxy().getInstance(Worker.class);
        String s = instance.doSomething();
        System.err.println(s);

    }

}
