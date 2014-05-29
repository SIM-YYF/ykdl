package com.ykdl.tangyoubang.Event;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

/**
 * Created by admin on 2014/5/10.
 */
@EBean
public class UserEvent {

    private String name = "小马";
    private String password = "123456";
    private String json;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
    public UserEvent(){}

    @UiThread
    public void builder(String json){
       this.json = json;
    }
}
