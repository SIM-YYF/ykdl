package com.ykdl.tangyoubang;

import com.ykdl.tangyoubang.Event.UserEvent;
import com.ykdl.tangyoubang.RestClient.TybApi;

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
    TybApi  api;
    @App
    TybApplication  application;
   public final  EventBus  BUS = application.BUS;

    @Bean
    UserEvent  userEvent;
    @Background
    public void getUser(){
        String json = api.getGoods();
        userEvent.builder(json);
        BUS.post(userEvent);
    }
    @Background
    public void login(){
        String json = api.login("fly80626", "fly80626");
        userEvent.builder(json);
        BUS.post(userEvent);
    }




}
