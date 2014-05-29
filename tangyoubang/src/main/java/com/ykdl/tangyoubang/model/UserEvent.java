package com.ykdl.tangyoubang.model;

import android.content.Context;

import com.ykdl.tangyoubang.model.protocol.User;

import org.androidannotations.annotations.EBean;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by admin on 2014/5/10.
 */

@EBean
public class UserEvent extends BaseEvent {



    public UserEvent(Context context) {
        super(context);
    }


    public void getUser() {
    }

}
