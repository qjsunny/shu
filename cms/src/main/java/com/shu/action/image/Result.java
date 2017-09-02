package com.shu.action.image;

/**
 * Created by james on 2017/5/8.
 */
public class Result {

    private String result;

    private String message;

    public Result() {
    }

    public Result(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
