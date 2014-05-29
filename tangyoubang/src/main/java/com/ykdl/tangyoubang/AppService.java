package com.ykdl.tangyoubang;



import com.ykdl.net.Client.RestHelper;
import com.ykdl.tangyoubang.Rest.TybApi;
import com.ykdl.tangyoubang.model.BaseEvent;
import com.ykdl.tangyoubang.model.CaptchaEvent;
import com.ykdl.tangyoubang.model.protocol.Captcha;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
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
    public TybApplication  application;

    public static final String NETWORK = "NETWORK";
    public static final String CACHE = "CACHE";

    //////////////////////////  第一种方式
    @Background(serial = "get_captcha_id")
    public void get_captcha(){
        //获得生成的验证码id
        Captcha captcha = api.get_captcha();
        get_captcha_stream(captcha.captcha_id);
    }
    @Background(serial = "show_captcha_stream")
    public void get_captcha_stream(String captcha_id){
        byte[]  bytes = api.show_captcha(captcha_id);
        application.BUS.post(bytes);
    }

    /////////////////////////// 第二种方式
    @Background
    public void get_captcha(BaseEvent.TybCallBack callBack){
        //获得生成的验证码id
//        String   str =  RestHelper.getRest().get("http://172.16.22.68:5000/tyb/api/v1/captcha/request", String.class).getBody();
        Captcha captcha = api.get_captcha();
        callBack.execute(captcha);
    }
    @Background
    public void get_captcha_stream(BaseEvent.TybCallBack callBack, String captcha_id){
        byte[]  bytes = api.show_captcha(captcha_id);
        callBack.execute(bytes);
    }
}
