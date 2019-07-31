package com.chenlong.common.http;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.List;

@JsonSerialize
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {


    private Integer code;
    private String  error;
    private Boolean ok;
    private Long count;
    private T data;


    /**
     * 状态编码
     *
     * @return
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 状态编码
     *
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 错误信息
     *
     * @return
     */
    public String getError() {
        return error;
    }

    /**
     * 错误信息
     *
     * @param error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * 返回数据
     *
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     * 返回数据
     *
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * 是否成功. (true:成功; false:失败)
     *
     * @return
     */
    public Boolean getOk() {
        return ok;
    }

    /**
     * 是否成功. (true:成功; false:失败)
     *
     * @param ok
     */
    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
    public Result() {
    }

    private Result(int code, String error, T data, Boolean ok) {
        this.code = code;
        this.error = error;
        this.data = data;
        this.ok = ok;
    }

    public static <T> Result<T> create(ResultCode respondCode, T data) {
        return new Result<T>(respondCode.getCode(), respondCode.getDescription(), data, respondCode.getOk());
    }

    public static <T> Result<T> create(ResultCode respondCode) {
        return new Result<T>(respondCode.getCode(), respondCode.getDescription(), null, respondCode.getOk());
    }

    /**
     * 创建成功信息
     *
     * @param data 返回给客户端的信息
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), null, data, ResultCode.SUCCESS.getOk());
    }

    /**
     * 创建成功信息
     *
     * @param page 返回给客户端的信息
     * @return
     */
    public static <T> Result<List<T>> success(IPage<T> page) {
        Result<List<T>> result = new Result<List<T>>(ResultCode.SUCCESS.getCode(), null, page.getRecords(),
                ResultCode.SUCCESS.getOk());
        result.setCount(page.getTotal());
        return result;
    }


    /**
     * 创建成功信息
     *
     * @return
     */
    public static <T> Result<T> success() {
        return new Result<T>(ResultCode.SUCCESS.getCode(), null, null, ResultCode.SUCCESS.getOk());
    }

    /**
     * 创建失败信息
     *
     * @param data 返回给客户端的信息
     * @return
     */
    public static <T> Result<T> error(T data) {
        return new Result<T>(ResultCode.ERROR.getCode(), null, data, ResultCode.ERROR.getOk());
    }

    /**
     * 创建失败信息
     *
     * @param error 返回给客户端的信息
     * @return
     */
    public static <T> Result<T> error(String error) {
        return new Result<T>(ResultCode.ERROR.getCode(), error, null, ResultCode.ERROR.getOk());
    }

    /**
     * 创建失败信息
     *
     * @return
     */
    public static <T> Result<T> error() {
        return new Result<T>(ResultCode.ERROR.getCode(), null, null, ResultCode.ERROR.getOk());
    }
}
