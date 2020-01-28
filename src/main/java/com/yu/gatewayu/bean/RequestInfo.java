package com.yu.gatewayu.bean;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Flux;

public class RequestInfo {
    private String serverName; //请求服务名

    private String path; //请求路径

    private HttpMethod httpMethod; //请求方法

    private HttpHeaders httpHeaders; //请求头

    private Flux<DataBuffer> body; //请求体

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public Flux<DataBuffer> getBody() {
        return body;
    }

    public void setBody(Flux<DataBuffer> body) {
        this.body = body;
    }
}
