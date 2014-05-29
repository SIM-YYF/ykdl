package com.ykdl.tangyoubang.model;

import android.content.Context;
import android.widget.Toast;

import com.ykdl.tangyoubang.AppService;
import com.ykdl.tangyoubang.Rest.TybApi;
import com.ykdl.tangyoubang.TybApplication;
import com.ykdl.tangyoubang.model.protocol.User;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.api.rest.RestErrorHandler;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestClientException;

import java.io.Serializable;

import de.greenrobot.event.EventBus;

/**
 * Created by yuanwenfei on 2014/5/28.
 */

@EBean
public class BaseEvent  implements Serializable {
    @App
    public TybApplication application;
    @Bean
    public AppService  appService;
    public Context mContext;
    public EventBus BUS;
    public BaseEvent(Context context){
        this.mContext = context;
    }
    @AfterInject
    public void init(){
        EventBus.getDefault().register(this);
        BUS = application.BUS;
        appService.api.setHeader("access_token", "354234523452345234");
        appService.api.setRestErrorHandler(new MRestErrorHandler());
    }

    public void errorCallBack(String error){
//        Toast.makeText(mContext,error,Toast.LENGTH_LONG).show();
//        EventBus.getDefault().post(error);
    }

    class MRestErrorHandler implements RestErrorHandler {
        @Override
        public void onRestClientExceptionThrown(RestClientException e) {
            EventBus.getDefault().post(e.getMessage());
        }
    }


    public interface TybCallBack{
        public void execute(Object json);
    }


    @UiThread
    public void onEvent(Object event){
        Toast.makeText(mContext,event.toString(),Toast.LENGTH_LONG).show();
    }
}
