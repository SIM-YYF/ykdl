package com.ykdl.net.Client;

import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Created by yuanwenfei on 2014/5/28.
 */
public class RequestParams {
    public MultiValueMap multiValueMap;
    public RequestParams() {
        multiValueMap =  new LinkedMultiValueMap();
    }
    public MultiValueMap put(String key, Object value){
        multiValueMap.put(key, value);
        return multiValueMap;
    }
}
