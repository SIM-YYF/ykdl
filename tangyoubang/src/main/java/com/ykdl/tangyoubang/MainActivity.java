package com.ykdl.tangyoubang;



import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;
import com.ykdl.net.Client.RestHelper;
import com.ykdl.tangyoubang.model.BaseEvent;
import com.ykdl.tangyoubang.model.CaptchaEvent;
import com.ykdl.tangyoubang.model.UserEvent;
import com.ykdl.tangyoubang.Rest.TybApi;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;


@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
    @ViewById(R.id.btn_get)
    Button btn_get;
    @App
    TybApplication  application;

    @Bean(CaptchaEvent.class)
    CaptchaEvent captchaEvent;
    @Bean
    AppService appService;

    @AfterInject
    public void initData(){

    }

    @UiThread
    public void onEvent(Object event){
        Toast.makeText(this, event.toString(), Toast.LENGTH_LONG).show();
    }
    @Click(R.id.btn_get)
    public void mybutton(){
        //第一种方式
        appService.get_captcha();
        //第二种方式
//        captchaEvent.get_captcha();
    }


    @Override
    protected void onStart() {
        super.onStart();
        application.BUS.register(this);
    }

    @Override
    protected void onStop() {
        super.onDestroy();
        application.BUS.unregister(this);
    }
}
