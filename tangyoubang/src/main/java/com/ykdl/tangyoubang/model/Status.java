package com.ykdl.tangyoubang.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by admin on 2014/6/1.
 */
public class Status implements Serializable {
    public int error_code;
    public String error_desc;

    public static Status fromJson(JSONObject jsonObject)  throws JSONException
    {
        if(null == jsonObject){
            return null;
        }
        Status   localItem = new Status();
        localItem.error_code = jsonObject.optInt("error_code");
        localItem.error_desc = jsonObject.optString("error_desc");
        return localItem;
    }
}
