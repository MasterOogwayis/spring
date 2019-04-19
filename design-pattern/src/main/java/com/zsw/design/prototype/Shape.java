package com.zsw.design.prototype;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Shaowei Zhang on 2019/3/19 22:52
 **/
@Getter
@Setter
public abstract class Shape<T> extends DeepCloneable<T> {

    private static final long serialVersionUID = 2949324342943486832L;
    private String id;
    protected String type;

    abstract void draw();

}
