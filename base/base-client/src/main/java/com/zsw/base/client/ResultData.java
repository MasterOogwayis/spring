package com.zsw.base.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZhangShaowei on 2017/5/26 17:46
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultData<T> implements Serializable {

    private static final String SUCCESS = "success";

    /**
     * 消息code:成功
     */
    public static final int RESULT_CODE_SUCCESS = 0;

    /**
     * 消息code:失败
     */
    public static final int RESULT_CODE_FAIL = 1;

    /**
     *
     */
    private Integer code;

    /**
     *
     */
    private String message;

    private String error;

    /**
     *
     */
    private T data;

    /**
     *
     */
    private List<T> dataList;


    /**
     * @param data
     */
    public ResultData(T data) {
        this.data = data;
        this.code = RESULT_CODE_SUCCESS;
        this.message = SUCCESS;
    }

    /**
     * @param dataList
     */
    public ResultData(List<T> dataList) {
        this.dataList = dataList;
        this.code = RESULT_CODE_SUCCESS;
        this.message = SUCCESS;
    }

    /**
     * @param code
     * @param message
     */
    public ResultData(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultData(String error) {
        this.code = RESULT_CODE_FAIL;
        this.error = error;
    }

    public static ResultData failure(String error) {
        return new ResultData(error);
    }
}
