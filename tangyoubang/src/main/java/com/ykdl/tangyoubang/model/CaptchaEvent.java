package com.ykdl.tangyoubang.model;

import android.content.Context;

import com.ykdl.tangyoubang.model.protocol.Captcha;
import com.ykdl.tangyoubang.model.protocol.User;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

/**
 * Created by admin on 2014/5/10.
 */

@EBean
public class CaptchaEvent extends BaseEvent {
    public CaptchaEvent(Context context) {
        super(context);
    }

//    public void get_captcha(){
//        appService.get_captcha(new TybCallBack() {
//            @Override
//            public void execute(Object json) {
//                String captcha_id = null;
////                errorCallBack(json);
//                    captcha_id = ((Captcha)json).captcha_id;
//                    get_captcha_by_captchaId(captcha_id);
//            }
//        });
//    }
//
//    private void get_captcha_by_captchaId(String captcha_id){
//        appService.get_captcha_stream(new TybCallBack() {
//            @Override
//            public void execute(Object json) {
//                appService.application.BUS.post(json);
////                  String str = json.toString();
////                errorCallBack(json);
////                BUS.post(json);
//            }
//        }, captcha_id);
//    }


}
