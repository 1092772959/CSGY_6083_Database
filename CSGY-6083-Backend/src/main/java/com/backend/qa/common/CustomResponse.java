package com.backend.qa.common;

import java.io.Serializable;

public class CustomResponse<T> extends CustomAbstractResponse implements Serializable {
    private static final long serialVersionUID = 867933019328199779L;
    private T data;
    private Integer count;

    protected CustomResponse(CustomResponseStatus status, String message) {
        super(status, message);
    }
    protected CustomResponse(CustomResponseStatus status) {
        super(status);
    }

    public static <T> CustomResponse<T> build() {
        return new CustomResponse(CustomResponseStatus.SUCCESS, (String)null);
    }

    public static <T> CustomResponse<T> build(String message) {
        return new CustomResponse(CustomResponseStatus.SUCCESS, message);
    }

    public static <T> CustomResponse<T> error(CustomResponseStatus status) {
        return new CustomResponse<T>(status);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void success(T value) {
        this.success();
        this.data = value;
        this.count = 0;
    }

}
