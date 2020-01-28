package com.yu.gatewayu.controller;

import com.yu.gatewayu.bean.RequestInfo;
import com.yu.gatewayu.handler.WebClientRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class GatewayuController {
    @Autowired
    private WebClientRequestHandler webClientRequestHandler;

    @RequestMapping("/**")
    public Mono<Void> forwardRequest(ServerWebExchange exchange) {
        System.out.println(1);
        RequestInfo requestInfo = extractRequestInfo(exchange.getRequest());
        ServerHttpResponse response = exchange.getResponse();

        //调用服务
        Mono<ClientResponse> responseMono = webClientRequestHandler.forward(requestInfo);
        return responseMono.flatMap(backendResponse -> {
            response.setStatusCode(backendResponse.statusCode());
            response.getHeaders().putAll(backendResponse.headers().asHttpHeaders());
            return response.writeWith(backendResponse.bodyToFlux(DataBuffer.class));
        });
    }

    private RequestInfo extractRequestInfo(ServerHttpRequest request) {
        RequestInfo requestInfo = new RequestInfo();
        String urlInfo[] = request.getPath().pathWithinApplication().value().split("/", 3);
        requestInfo.setServerName(urlInfo[1]);
        requestInfo.setPath(urlInfo[2]);

        requestInfo.setHttpMethod(request.getMethod());
        requestInfo.setHttpHeaders(request.getHeaders());

        if (requireHttpBody(request.getMethod())) {
            requestInfo.setBody(request.getBody());
        }
        return requestInfo;
    }

    private boolean requireHttpBody(HttpMethod method) {
        return HttpMethod.POST == method || HttpMethod.PUT == method ||
                HttpMethod.PATCH == method || HttpMethod.TRACE == method ;
    }
}
