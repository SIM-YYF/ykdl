package com.ykdl.tangyoubang;



import com.ykdl.tangyoubang.Events.UserEvent;
import com.ykdl.tangyoubang.RestClient.TybApi;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;


import de.greenrobot.event.EventBus;

/**
 * Created by admin on 2014/5/10.
 */
@EBean(scope = EBean.Scope.Singleton)
public class AppService {

    @RestService
    public TybApi api;
    @App
    public TybApplication application;
    public final EventBus BUS = application.BUS;

    public static final String NETWORK = "NETWORK";
    public static final String CACHE = "CACHE";

    @Background(serial = CACHE)
    public String getUser() {
        //从缓存中读取对象
        BUS.post("cacheResult");
        //从网络中获得数据
        return getUserAsync();
    }
    @Background(serial = NETWORK)
    public String getUserAsync(){
        api.setHeader("username", "value");
        return api.getUser("android");
    }


}
