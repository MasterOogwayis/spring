package com.zsw.orm.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TypeE {

    /**
     *
     */
    HUMAN(1, "人类"),
    ANIME(2, "动物")
    ;

    private Integer code;

    private String desc;

}
