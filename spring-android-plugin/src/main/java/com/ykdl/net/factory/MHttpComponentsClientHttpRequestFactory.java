package com.ykdl.net.factory;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**
 * Created by yuanwenfei on 2014/5/27.
 */
public class MHttpComponentsClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {
    private static MHttpComponentsClientHttpRequestFactory  instance = null;
    private MHttpComponentsClientHttpRequestFactory(){ }
    public synchronized static MHttpComponentsClientHttpRequestFactory getInstance(){
        if(instance == null){
            instance = new MHttpComponentsClientHttpRequestFactory();
        }
        return instance;
    }
}
