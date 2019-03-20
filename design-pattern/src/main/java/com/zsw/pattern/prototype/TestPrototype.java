package com.zsw.pattern.prototype;

/**
 * 原型模式 - 用于创建重复的对象，又能保证性能
 *
 * 用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。
 *
 * @author ZhangShaowei on 2019/3/20 9:13
 **/
public class TestPrototype {

    public static void main(String[] args) {

        Shop shop = new Shop(new Drinks("雪碧"), new Snacks("辣条"));
//        Shop clone = shop.clone();
//        System.out.println(shop.equals(clone));
//        System.out.println(shop.getDrinks().equals(clone.getDrinks()));
//        System.out.println(shop.getSnacks().equals(clone.getSnacks()));

        Shop deepClone = shop.deepClone();
        System.out.println(shop.equals(deepClone));
        System.out.println(shop.getDrinks().equals(deepClone.getDrinks()));
        System.out.println(shop.getSnacks().equals(deepClone.getSnacks()));

    }

}
