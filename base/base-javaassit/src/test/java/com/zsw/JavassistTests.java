package com.zsw;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;

/**
 * @author ZhangShaowei on 2020/4/2 15:09
 */
public class JavassistTests {

    private static final ClassPool CLASS_POOL = ClassPool.getDefault();

    public static void main(String[] args) throws Exception {
        CtClass ctClass = CLASS_POOL.get("com.zsw.Teacher");
        CtConstructor declaredConstructor = ctClass.getDeclaredConstructor(new CtClass[0]);
        declaredConstructor.setBody("{}");

        Object o = ctClass.toClass().newInstance();
        System.out.println(o);
    }


    public void doSomething() { System.out.println("Do nothing..."); }


    public static void test() throws Exception {
        CtClass waiter = CLASS_POOL.get("com.zsw.Waiter");
        CtClass worker = CLASS_POOL.get("com.zsw.Worker");
        waiter.setInterfaces(new CtClass[]{worker});
        waiter.writeFile();
        Class<?> clazz = waiter.toClass();
        Worker o = (Worker) clazz.newInstance();
        System.out.println(o.getName());


        CtClass homeless = CLASS_POOL.makeClass("com.zsw.Homeless");
        homeless.setInterfaces(new CtClass[]{worker});
        homeless.setSuperclass(waiter);

//        homeless.writeFile();
//        homeless.defrost();

        CtMethod doSomething = CtNewMethod.make("public void doSomething() { System.out.println(\"Do nothing...\"); }", homeless);
        homeless.addMethod(doSomething);

        Worker w1 = (Worker) homeless.toClass().newInstance();

        System.err.println(w1.getName());
        w1.doSomething();
    }

}
