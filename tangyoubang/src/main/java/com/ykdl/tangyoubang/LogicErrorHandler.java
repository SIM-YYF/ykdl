package com.ykdl.tangyoubang;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.ykdl.tangyoubang.model.Status;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

/**
 * Created by admin on 2014/6/1.
 * 公共的业务逻辑错误处理类
 */
@EBean(scope = EBean.Scope.Singleton)
public class LogicErrorHandler {
    private Context mContext;
    private static final String TAG = "LogicErrorHandler";
    public  void builder(Context context){
        mContext = context;
        EventBus.getDefault().register(this);
    }

    public boolean isLogicError(Object object){
        boolean isError = false;
        if(object == null){
            isError = true;
            return isError;
        }

        try {
                if (object instanceof String) {
                    JSONObject jsonObject = new JSONObject(object.toString());
                    if (!jsonObject.isNull("error_code")) {
                        Status status = Status.fromJson(jsonObject);
                        EventBus.getDefault().post(status);
                        isError = true;
                    }
                } else if (object instanceof Status) {
                    Status status = (Status) object;
                    if (status.error_code > 0) {
                        EventBus.getDefault().post(status);
                        isError = true;
                    }
                }
        }catch (JSONException e){
            e.printStackTrace();
            isError = true;
        }
        return isError;
    }

    @UiThread
    public void onEvent(Status status){
        Log.d(TAG, status.error_code + "");
        Toast.makeText(mContext, status.error_code + "", Toast.LENGTH_LONG).show();
    }

}
