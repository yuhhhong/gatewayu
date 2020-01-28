package com.yu.gatewayu.bean;

public class FaleException extends RuntimeException {
    private String faleState;

    private String msg;

    public FaleException(String faleState, String msg) {
        this.faleState = faleState;
        this.msg = msg;
    }

    public String getFaleState() {
        return faleState;
    }

    public void setFaleState(String faleState) {
        this.faleState = faleState;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
