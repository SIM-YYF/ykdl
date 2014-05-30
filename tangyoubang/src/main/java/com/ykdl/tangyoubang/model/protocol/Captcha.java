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
}
