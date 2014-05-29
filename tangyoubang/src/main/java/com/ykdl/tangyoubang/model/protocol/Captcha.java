package com.ykdl.tangyoubang.model.protocol;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 验证码模型数据
 */
public class Captcha {
    /**
     * 验证码id
     */
    public String captcha_id;
    /**
     * 验证验证码结果状态
     */
    public String verify_status;

    public static Captcha fromJson(JSONObject jsonObject) throws JSONException {
        if(null == jsonObject){
            return null;
        }
        Captcha   localItem = new Captcha();
        localItem.captcha_id = jsonObject.optString("captcha_id");
        localItem.verify_status = jsonObject.optString("verify_status");
        return localItem;
    }
    public JSONObject  toJson() throws JSONException
    {
        JSONObject localItemObject = new JSONObject();
        localItemObject.put("captcha_id", captcha_id);
        localItemObject.put("verify_status", verify_status);
        return localItemObject;
    }
}
