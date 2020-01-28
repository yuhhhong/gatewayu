package com.yu.gatewayu.finals;

public class ResultsState {
    public static final String STATE_AUTH_FAIL = "AUTHFAIL"; //授权失败
    public static final String STATE_OK = "OK";
    public static final String STATE_FAIL = "FAIL";
    public static final String STATE_LIMITED_AUTHORITY = "LIMITEDAUTHORITY"; //权限不足
    public static final String STATE_INTERNAL_ERROR = "INTERNALERROR"; //内部错误
    public static final String STATE_FORWARD_FAIL= "FORWARDFAIL"; //转发失败
}
