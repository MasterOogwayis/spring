package com.demo.factories;

/**
 * @author Administrator on 2019/9/8 14:42
 **/
public class DoNothing implements DoSomething {
    @Override
    public void execute() {
        System.err.println("do nothing.");
    }
}
