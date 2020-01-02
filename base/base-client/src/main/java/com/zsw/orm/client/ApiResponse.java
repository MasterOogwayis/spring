package com.zsw.orm.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zsw.orm.client.exception.ApiException;
import com.zsw.orm.client.exception.BaseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.zsw.orm.client.exception.BaseExceptionType.SUCCESS;

/**
 * @author ZhangShaowei on 2017/5/26 17:46
 */
@Getter
@Setter
@Builder
@NoArgsConstructor(onConstructor_ = {@Deprecated})
@AllArgsConstructor(onConstructor_ = {@Deprecated})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = 8511737922403851422L;
    /**
     * 消息code:成功
     */
    public static final int RESULT_CODE_SUCCESS = SUCCESS.getCode();

    /**
     * 消息code:失败
     */
    public static final int RESULT_CODE_FAIL = -1;

    public static final String FAIL = "FAIL";


    /**
     *
     */
    private Integer code;

    /**
     *
     */
    private String message;

    /**
     *
     */
    private T data;

    @JsonIgnore
    public boolean isSuccess() {
        return Objects.equals(RESULT_CODE_SUCCESS, this.code);
    }


    public static <R> ApiResponse<R> success() {
        return success(null);
    }

    public static <R> ApiResponse<R> of(String message) {
        return build(RESULT_CODE_SUCCESS, message);
    }

    public static <R> ApiResponse<R> success(R data) {
        return ApiResponse.<R>builder().code(RESULT_CODE_SUCCESS).message(SUCCESS.getMessage()).data(data).build();
    }

    public static <R> ApiResponse<R> fail() {
        return fail(FAIL);
    }

    public static <R> ApiResponse<R> fail(String message) {
        return build(RESULT_CODE_FAIL, message);
    }

    public static <R> ApiResponse<R> build(BaseType type) {
        return build(type.getCode(), type.getMessage());
    }

    public static <R> ApiResponse<R> fail(ApiException e) {
        return build(e.getType(), e);
    }

    public static <R> ApiResponse<R> build(BaseType type, Throwable t) {
        return build(type.getCode(), t.getMessage() + " : " + type.getMessage());
    }

    public static <R> ApiResponse<R> build(Integer code, String message) {
        return ApiResponse.<R>builder().code(code).message(message).build();
    }


    /**
     * @deprecated 直接使用 T 作为泛型 ，不需要 List<T> 这种结构了
     */
    @Deprecated
    private List<T> dataList;

    /**
     * @param dataList
     * @param <R>
     * @return
     * @deprecated 兼容旧数据 List<T> -> T
     */
    @Deprecated
    public static <R> ApiResponse<R> dataList(List<R> dataList) {
        return ApiResponse.<R>builder().code(RESULT_CODE_SUCCESS).message(SUCCESS.getMessage()).dataList(dataList).build();
    }


    /**
     * @param dataList
     */
    @Deprecated
    public ApiResponse(List<T> dataList) {
        this.dataList = dataList;
        this.code = SUCCESS.getCode();
        this.message = SUCCESS.getMessage();
    }


    public ApiResponse<?> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (!this.isSuccess()) {

        }
        return predicate.test(this.data) ? this : fail();
    }


    /*
      --- 下面是增强方法 ---
     */

    /**
     * @param consumer Consumer
     */
    public void isSuccess(Consumer<? super T> consumer) {
        if (this.isSuccess()) {
            consumer.accept(this.data);
        }
    }

    /**
     * T -> R
     *
     * @param mapper Function
     * @param <R>    mapped data
     * @return ApiResponse<R>
     */
    public <R> ApiResponse<R> map(Function<? super T, ? extends R> mapper) {
        if (!this.isPresent()) {
            return fail(this.message);
        }
        return success(mapper.apply(this.data));
    }


    /**
     * 成功 且 数据不为空
     *
     * @return isPresent
     */
    public boolean isPresent() {
        return this.isSuccess() && Objects.nonNull(this.data);
    }


}
