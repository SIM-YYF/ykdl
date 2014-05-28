package com.ykdl.tangyoubang.Events;

import android.content.Context;

import com.ykdl.tangyoubang.AppService;
import com.ykdl.tangyoubang.Events.models.User;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.api.rest.RestErrorHandler;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestClientException;

import java.io.Serializable;

/**
 * Created by yuanwenfei on 2014/5/28.
 */

@EBean
public class BaseEvent  implements Serializable {
    //初始化请求服务
    @Bean
    public AppService  appService;
    public Context mContext;

    public BaseEvent(Context context){
        this.mContext = context;
        appService.api.setHeader("access_token", "354234523452345234");
        appService.api.setRestErrorHandler(new MRestErrorHandler(context));
    }

    public void errorCallBack(String json){
        try {
            User responseStatus = User.fromJson(new JSONObject().optJSONObject("status"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class MRestErrorHandler implements RestErrorHandler {
        private Context mContext;
        public MRestErrorHandler(Context context){
            this.mContext = context;
        }
        @Override
        public void onRestClientExceptionThrown(RestClientException e) {

        }
    }

}
