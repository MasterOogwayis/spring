package com.zsw.pattern.prototype;

import com.zsw.pattern.DeepCloneable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ZhangShaowei on 2019/3/20 9:15
 **/
@Getter
@Setter
@AllArgsConstructor
class Shop extends DeepCloneable<Shop> {
    private static final long serialVersionUID = 8124832638348523302L;

    Drinks drinks;

    Snacks snacks;



}
