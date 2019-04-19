package com.zsw.design.prototype;

/**
 * 原型模式，用于创建重复的对象
 * 用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象
 *
 * @author Shaowei Zhang on 2019/3/19 22:41
 **/
public class TestPrototype {

    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.setId("1");

        System.out.println("target:" + circle);
        Circle deepClone = circle.deepClone();
        System.out.println("deepClone:" + deepClone);
        System.err.println(circle.equals(deepClone));
    }

}
