package com.yu.gatewayu.controller;

import com.yu.gatewayu.bean.ResponseResults;
import com.yu.gatewayu.finals.ExceptionControllerFinal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

@RestController
public class ExceptionController {
    @RequestMapping(ExceptionControllerFinal.EXCEPTION_CONTROLLER_PATH)
    public ResponseResults exceptionController(ServerWebExchange exchange) {
        String type = exchange.getAttribute(ExceptionControllerFinal.EXCEPTION_TYPE);
        return new ResponseResults(type, "");
    }
}
