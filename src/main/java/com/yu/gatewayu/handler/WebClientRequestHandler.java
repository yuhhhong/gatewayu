package com.yu.gatewayu.handler;

import com.yu.gatewayu.bean.FaleException;
import com.yu.gatewayu.bean.RequestInfo;
import com.yu.gatewayu.finals.ResultsState;
import com.yu.gatewayu.interfaces.DiscoveryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class WebClientRequestHandler {
    @Autowired
    private DiscoveryHandler discoveryHandler;

    @Value("${gatewayu.forward.timeout}")
    private long timeout;

    private WebClient webClient = WebClient.create();

    public Mono<ClientResponse> forward(RequestInfo requestInfo) {
        String url = discoveryHandler.getUrlByName(requestInfo.getServerName())+ "/" + requestInfo.getPath();

        //创建请求
        WebClient.RequestBodySpec reqBodySpec = webClient.method(requestInfo.getHttpMethod()).
                uri(url).
                headers(httpHeaders ->
                {
                    httpHeaders.addAll(requestInfo.getHttpHeaders());
                    httpHeaders.remove("HOST");
                });

        WebClient.RequestHeadersSpec<?> reqHeadersSpec;
        if (requestInfo.getBody() != null) {
            reqHeadersSpec = reqBodySpec.body(BodyInserters.fromDataBuffers(requestInfo.getBody()));
        } else {
            reqHeadersSpec = reqBodySpec;
        }

        //调用服务
        Mono<ClientResponse> responseMono = reqHeadersSpec.exchange().timeout(Duration.ofMillis(timeout))
                .onErrorResume(ex -> {
                    throw new FaleException(ResultsState.STATE_FORWARD_FAIL, "转发失败");
                });
        return responseMono;
    }
}
