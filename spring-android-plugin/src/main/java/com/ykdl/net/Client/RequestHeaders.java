package com.ykdl.net.Client;

import org.springframework.http.HttpHeaders;

/**
 * Created by yuanwenfei on 2014/5/28.
 */
public class RequestHeaders {
    public HttpHeaders httpHeaders;
    public RequestHeaders() {
        httpHeaders =  new HttpHeaders();
    }
    public HttpHeaders setHeader(String key, String value){
        httpHeaders.add(key, value);
        return httpHeaders;
    }

}
