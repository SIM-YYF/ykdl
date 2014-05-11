package com.ykdl.tangyoubang.RestClient.Handler;

import org.androidannotations.annotations.EBean;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.web.client.RestClientException;

/**
 * Created by yuanwenfei on 2014/5/9.
 */

@EBean
public class TybRestErrorHandler implements RestErrorHandler {
    @Override
    public void onRestClientExceptionThrown(RestClientException e) {
        // Do whatever you want here.(处理所用请求的错误反馈信息)
    }
}
