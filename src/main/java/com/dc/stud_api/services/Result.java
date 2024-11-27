package com.dc.stud_api.services;

public class Result<T> {
    private T _data;
    private ErrorType _error;

    public Result(T data, ErrorType error) {
        _data = data;
        _error = error;
    }

    public boolean hasError() {
        return _error != null;
    }

    public T getData() {
        return _data;
    }

    public ErrorType getError() {
        return _error;
    }
}
