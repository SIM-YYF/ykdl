package com.ykdl.net.Client;


import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Created by yuanwenfei on 2014/5/27.
 */
public final class MRestTemplate  {
    private static final int TIMEOUT = 60 * 1000;
    private static HttpHeaders httpHeaders;
    private static HttpAuthentication authHeader;
    private static RestTemplate mInstance = null;
    public synchronized  static RestTemplate getInstance() {
        if(mInstance == null){
            mInstance = initializeRestTemplate();
        }
        return mInstance;
    }
    private static RestTemplate initializeRestTemplate() {
        initializeAuth();
        initializeHeaders();
        mInstance = new RestTemplate(true);
        return mInstance;
    }
    private static  void initializeAuth() {
        authHeader = new HttpBasicAuthentication("username", "password");
    }
    private static void initializeHeaders() {
        httpHeaders = new HttpHeaders();
        httpHeaders.add("access_token", "");
        httpHeaders.setAuthorization(authHeader);
        httpHeaders.setContentType(MediaType.ALL);
        httpHeaders.setAccept(Collections.singletonList(MediaType.ALL));
        httpHeaders.setAcceptEncoding(ContentCodingType.ALL);
    }
}
