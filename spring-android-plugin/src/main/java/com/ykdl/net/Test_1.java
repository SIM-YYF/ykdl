package com.ykdl.net;

import com.ykdl.net.Client.HttpRequestCommHeaders;
import com.ykdl.net.Client.Rest;
import com.ykdl.net.Client.RestHelper;

import org.springframework.http.HttpHeaders;

/**
 * Created by yuanwenfei on 2014/5/28.
 */
public class Test_1 {

    public void getUser(){
        Rest rest = RestHelper.getRest();
        rest.setHttpRequestCommHeaders(new HttpRequestCommHeaders() {
            @Override
            public HttpHeaders getCommHeaders() {
                return null;
            }
        });
        rest.get("url", String.class);
    }
}
