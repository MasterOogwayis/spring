package com.demo.factories;

/**
 * @author Administrator on 2019/9/8 14:42
 **/
public class Work implements DoSomething {


    @Override
    public void execute() {
        System.out.println("work");
    }
}
