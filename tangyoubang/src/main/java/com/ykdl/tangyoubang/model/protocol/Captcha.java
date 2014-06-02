package com.ykdl.tangyoubang.model.protocol;

import com.ykdl.tangyoubang.model.Status;

import java.io.Serializable;

/**
 * 验证码模型数据
 */
public class Captcha extends Status {
    /**
     * 验证码id
     */
    public String captcha_id;
    /**
     * 验证验证码结果状态
     */
    public String verify_status;

}
