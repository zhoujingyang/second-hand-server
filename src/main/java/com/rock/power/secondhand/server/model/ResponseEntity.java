package com.rock.power.secondhand.server.model;

/**
 * Created by yanshi on 16/7/31.
 */
public class ResponseEntity<T> {
    private boolean isSuccess = false;
    private T value;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
