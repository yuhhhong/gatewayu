package com.yu.gatewayu.handler;

import com.yu.gatewayu.bean.FaleException;
import com.yu.gatewayu.bean.ResponseResults;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AllExceptionHandler {
    @ExceptionHandler(value = FaleException.class)
    @ResponseBody
    private ResponseResults faleExceptionHandler(FaleException e) {
        return new ResponseResults(e.getFaleState(), e.getMsg());
    }
}
