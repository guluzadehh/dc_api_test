package com.dc.stud_api.common;

public class Result<T, U> {
    private T _data;
    private U _error;

    public Result(T data, U error) {
        _data = data;
        _error = error;
    }

    public boolean hasError() {
        return _error != null;
    }

    public T getData() {
        return _data;
    }

    public U getError() {
        return _error;
    }

    public static <T, U> Result<T, U> success(T data) {
        return new Result<T, U>(data, null);
    }

    public static <T, U> Result<T, U> error(U error) {
        return new Result<T, U>(null, error);
    }
}
