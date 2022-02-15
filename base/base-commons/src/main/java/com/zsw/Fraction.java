package com.zsw;

import lombok.AllArgsConstructor;

/**
 * @author ZhangShaowei on 2022/2/10 16:01
 */
@AllArgsConstructor
public class Fraction extends Number {

    private static final long serialVersionUID = 352278109591692915L;
    private final long molecular;
    private final long denominator;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fraction fraction = (Fraction) o;
        // 数字溢出风险
        return molecular * fraction.denominator == denominator * fraction.molecular;
    }

    @Override
    public int intValue() {
        return (int) longValue();
    }

    @Override
    public long longValue() {
        return molecular / denominator;
    }

    @Override
    public float floatValue() {
        return (float) molecular / (float) denominator;
    }

    @Override
    public double doubleValue() {
        return (double) molecular / (double) denominator;
    }


}
