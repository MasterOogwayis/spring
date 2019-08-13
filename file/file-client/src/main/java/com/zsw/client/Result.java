package com.zsw.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author ZhangShaowei on 2019/8/13 11:14
 **/
@Getter
@Setter
@Builder
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -4675732170535388785L;
    public Integer code;

    private T data;

    private String message;


    public static <R> Result<R> success(R r) {
        return Result.<R>builder().code(0).data(r).build();
    }


    public static <R> Result<R> faild(String message) {
        return Result.<R>builder().code(-1).message(message).build();
    }

    public boolean isSuccess() {
        return Objects.equals(this.code, 0);
    }


}
