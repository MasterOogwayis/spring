package com;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * @author ZhangShaowei on 2019/6/13 16:47
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoteResponse<T> implements Serializable {

    private static final long serialVersionUID = 7241632874336394828L;
    private T data;

    public static <R> RemoteResponse<R> data(R data) {
        return RemoteResponse.<R>builder().data(data).build();
    }

}
