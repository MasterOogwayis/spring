package com.zsw;

import com.zsw.commons.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author ZhangShaowei
 */
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum CommonsStatus implements Type {

    /**
     *
     */
    DISABLED(0, "禁用"),
    ENABLE(1, "启用"),
    UNKNOWN(99, "未知");

    private final int code;
    private final String desc;

    public static CommonsStatus valueOf(int i) {
        return Arrays.stream(values())
                .filter(s -> Objects.equals(s.getCode(), i))
                .findFirst()
                .orElse(UNKNOWN);
    }







}
