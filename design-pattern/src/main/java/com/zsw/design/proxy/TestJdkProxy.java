package com.zsw.design.proxy;

import com.zsw.design.proxy.jdk.JdkProxy;
import sun.misc.ProxyGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Shaowei Zhang on 2019/3/11 19:12
 **/
public class TestJdkProxy {

    public static void main(String[] args) throws IOException {

        ObjectTarget target = new JdkProxy().getInstance(new WorkTarget());
        target.doSomething();

        String name = "$Proxy0";
        String fileName = name + ".class";

        byte[] bytes = ProxyGenerator.generateProxyClass(name, new Class[]{ObjectTarget.class});

        Files.write(Paths.get(fileName), bytes, StandardOpenOption.CREATE);


    }

}
