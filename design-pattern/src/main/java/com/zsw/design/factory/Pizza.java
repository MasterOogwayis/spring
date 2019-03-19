package com.zsw.design.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Delegate;

import java.util.List;

/**
 * @author Shaowei Zhang on 2019/3/7 20:36
 **/
@Data
@AllArgsConstructor
public abstract class Pizza {

    String name;
    String dough;
    String sauce;

    @Delegate
    List<String> toppings;


    public void prepare() {
        System.out.println("准备中 " + this.getName());
        System.out.println("抛面团...");
        System.out.println("添加酱汁...");
        System.out.println("添加配料...");
        this.toppings.forEach(System.out::println);
    }

    ;

    public void bake() {
        System.out.println("350℃ 烤25分钟");
    }

    public void cut() {
        System.out.println("对角切片");
    }

    public void box() {
        System.out.println("标准打包");
    }


}
