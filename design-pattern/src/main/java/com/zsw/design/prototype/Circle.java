package com.zsw.design.prototype;

/**
 * @author Shaowei Zhang on 2019/3/19 22:55
 **/
public class Circle extends Shape<Circle> {
    public Circle() {
        this.type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
