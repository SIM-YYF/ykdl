package com.ykdl.tangyoubang.Events;

import android.content.Context;

import com.ykdl.tangyoubang.Events.models.User;

import org.androidannotations.annotations.EBean;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by admin on 2014/5/10.
 */

@EBean
public class UserEvent extends BaseEvent {

    private String name = "小马";
    private String password = "123456";

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

    public UserEvent(Context context) {
        super(context);
    }


    public String getUser() {
        String json = appService.getUser();
        this.errorCallBack(json);
        try {
            JSONObject jo = new JSONObject();
            User responseStatus = User.fromJson(jo.optJSONObject("user"));
            appService.BUS.post(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getName();

    }

}
