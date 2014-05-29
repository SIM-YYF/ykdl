package com.ykdl.tangyoubang.model.protocol;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yuanwenfei on 2014/5/28.
 */
public class User {

    public int id;
    public String username;
    public String password;

    public static User fromJson(JSONObject jsonObject) throws JSONException{
        if(null == jsonObject){
            return null;
        }
        User   localItem = new User();
        localItem.id = jsonObject.optInt("id");
        localItem.username = jsonObject.optString("username");
        localItem.password = jsonObject.optString("password");
        return localItem;
    }
    public JSONObject  toJson() throws JSONException
    {
        JSONObject localItemObject = new JSONObject();
        localItemObject.put("id", id);
        localItemObject.put("username", username);
        localItemObject.put("password", password);
        return localItemObject;
    }

}
