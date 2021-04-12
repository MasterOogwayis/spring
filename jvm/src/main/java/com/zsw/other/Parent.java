package com.zsw.other;

/**
 * @author ZhangShaowei on 2021/4/12 11:03
 */
public interface Parent<T extends Number> {

    default Number t(T number){
        return number;
    };

}
