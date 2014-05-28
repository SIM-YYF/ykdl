package com.ykdl.net.Client;

import org.springframework.http.HttpHeaders;

/**
 * Created by yuanwenfei on 2014/5/28.
 */
public class RestHelper {
    private static class RestHelperInstance {
        private static final Rest instance = new Rest();
    }
    public static Rest getRest() {
        return RestHelperInstance.instance;
    }
}
