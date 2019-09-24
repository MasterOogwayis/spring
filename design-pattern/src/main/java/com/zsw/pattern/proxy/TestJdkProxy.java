package com.zsw.pattern.proxy;

import lombok.SneakyThrows;

/**
 * @author ZhangShaowei on 2019/3/20 9:37
 **/
public class TestJdkProxy {

    @SneakyThrows
    public static void main(String[] args) {


//        DoSomething instance = new JdkProxy().getInstance(new Worker());
//        String s = instance.doSomething();
//        System.err.println(s);
//
//        String str = "123";
//        str.intern();
//
//        // 1. 自动生成 $Proxy0,class 文件
//        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // 2. 自己读取 bytes 写入文件 ，两种方式都行
//        String name = "$Proxy0";
//        String fileName = name + ".class";
//        byte[] bytes = ProxyGenerator.generateProxyClass(fileName, new Class[]{DoSomething.class});
//        Files.write(Paths.get(fileName), bytes, StandardOpenOption.CREATE);
    }

}
