package com.yu.gatewayu.filter;

import com.yu.gatewayu.finals.ExceptionControllerFinal;
import com.yu.gatewayu.finals.ResultsState;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Order(1)
@Component
public class RequestAuthFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        if(!authRequest(serverWebExchange)) {
            //request.mutate返回一个请求构建器(builder design pattern)，path方法修改请求的url，build方法返回新的request
            ServerHttpRequest authErrorReq = serverWebExchange.getRequest().mutate().path(ExceptionControllerFinal.EXCEPTION_CONTROLLER_PATH).build();
            //erverWebExchange.mutate类似，构建一个新的ServerWebExchange
            ServerWebExchange authErrorExchange = serverWebExchange.mutate().request(authErrorReq).build();
            authErrorExchange.getAttributes().put(ExceptionControllerFinal.EXCEPTION_TYPE, ResultsState.STATE_AUTH_FAIL);
            return webFilterChain.filter(authErrorExchange);
        }
        else {
            //forward to 反向代理reverse proxy controller
            return webFilterChain.filter(serverWebExchange);
        }
    }

    public boolean authRequest(ServerWebExchange serverWebExchange) {
        return true;
    }
}
