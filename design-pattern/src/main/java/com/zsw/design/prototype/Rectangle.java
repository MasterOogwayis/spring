package com.zsw.design.prototype;

/**
 * @author Shaowei Zhang on 2019/3/19 22:53
 **/
public class Rectangle extends Shape<Rectangle> {

    public Rectangle() {
        this.type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
