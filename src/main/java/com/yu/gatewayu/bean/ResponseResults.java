package com.yu.gatewayu.bean;

import com.yu.gatewayu.finals.ResultsState;

public class ResponseResults {
    //api结果状态
    private String state;
    //api响应提示信息
    private String msg;
    //api响应数据内容
    private Object data;

    public ResponseResults() {
        this.state = ResultsState.STATE_OK;
    }
    public ResponseResults(Object data) {
        this.state = ResultsState.STATE_OK;
        this.data = data;
    }
    public ResponseResults(String state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
