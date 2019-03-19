package com.zsw.design.proxy;

import com.zsw.design.proxy.cglib.CglibProxy;
import lombok.SneakyThrows;
import net.sf.cglib.core.DebuggingClassWriter;

/**
 * @author Shaowei Zhang on 2019/3/11 19:36
 **/
public class TestCglibProxy {

    /**
     * com.zsw.design.proxy.WorkTarget$$EnhancerByCGLIB$$6c12784c@4439f31e
     *
     * @param args
     */
    @SneakyThrows
    public static void main(String[] args) {

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "J:\\workplace\\spring");

        ObjectTarget target = new CglibProxy().getInstance(WorkTarget.class);
        String str = target.doSomething();
        System.err.println(str);




//        byte[] bytes = ProxyGenerator.generateProxyClass(name, new Class[]{ObjectTarget.class});
//
//        Files.write(Paths.get("CglibTarget.class"), bytes, StandardOpenOption.CREATE);

    }

}
