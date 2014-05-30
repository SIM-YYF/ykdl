package com.ykdl.tangyoubang;



import android.content.Context;

import com.ykdl.tangyoubang.Rest.TybApi;
import com.ykdl.tangyoubang.Rest.handler.MResponseErrorHandler;
import com.ykdl.tangyoubang.Rest.handler.MRestClientErrorHandler;
import com.ykdl.tangyoubang.model.protocol.Captcha;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.api.BackgroundExecutor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import de.greenrobot.event.EventBus;

/**
 * Created by admin on 2014/5/10.
 * 糖友帮所有数据业务逻辑
 */
@EBean(scope = EBean.Scope.Singleton)
public class AppService {

    @RootContext
    public Context mContext;

    @RestService
    public TybApi api;
    @App
    public TybApplication  application;

    public  EventBus bus;

    @Bean
    public MRestClientErrorHandler handler;

    @AfterInject
    public void init(){
        bus = application.BUS;
        api.setHeader("access_token", "354234523452345234");
        RestTemplate  restTemplate = new RestTemplate(true);
        /**
         * 这里处理I/O error: null; nested exception is java.io.EOFException该异常
         * 1.0.1版本的漏洞。使用了URLConnection
         * 1.0.2版本使用了 HttpClient
         * 进行的设置的
         * http://stackoverflow.com/questions/13182519/spring-rest-template-usage-causes-eofexception
         */
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(10 * 1000);
        factory.setReadTimeout(10 * 1000);
        restTemplate.setRequestFactory(factory);
        restTemplate.setErrorHandler(new MResponseErrorHandler());
        handler.setErrorHandler(mContext);
        api.setRestTemplate(restTemplate);
        api.setRestErrorHandler(handler);
    }

    @Background(serial = "get_captcha_id", id = "get_captcha_id")
    public void get_captcha(){
        //获得生成的验证码id
        Captcha captcha = api.get_captcha();
//        bus.post(captcha);
//        //获得显示验证码图片流
        get_captcha_stream(captcha.captcha_id);
    }
    @Background(serial = "show_captcha_stream", id = "show_captcha_stream", delay = 500)
    public void get_captcha_stream(String captcha_id){
        byte[]  bytes = api.show_captcha(captcha_id);
        bus.post(bytes);

    }

    public void cancelTask(boolean isRunning, String ... task_ids){
        for(int i = 0; i < task_ids.length; i++){
            BackgroundExecutor.cancelAll(task_ids[i], isRunning);
        }

    }

}
