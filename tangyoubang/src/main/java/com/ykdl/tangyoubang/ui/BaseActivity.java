package com.ykdl.tangyoubang.ui;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.ykdl.tangyoubang.AppService;
import com.ykdl.tangyoubang.TybApplication;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import de.greenrobot.event.EventBus;

/**
 * Created by yuanwenfei on 2014/5/30.
 */
@EActivity
public abstract class BaseActivity extends FragmentActivity {
    @Bean
    AppService  appService;
    @App
    TybApplication application;

    public static EventBus bus;
    @AfterInject
    public void init(){
        bus = application.BUS;
    }




    @Override
    protected void onStop() {
        super.onStop();
        bus.unregister(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bus.register(this);
    }



}
