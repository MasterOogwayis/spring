package com.zsw.design.prototype;

/**
 * @author Shaowei Zhang on 2019/3/19 22:55
 **/
public class Square extends Shape<Square> {
    public Square() {
        this.type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
