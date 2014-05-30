package com.ykdl.tangyoubang.Rest.handler;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.api.rest.RestErrorHandler;
import org.apache.http.conn.HttpHostConnectException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import de.greenrobot.event.EventBus;

/**
 * Created by yuanwenfei on 2014/5/29.
 */

@EBean
public class MRestClientErrorHandler implements RestErrorHandler {
    private Context mContext;
    private static final String TAG = "MRestErrorHandler";

    public void setErrorHandler(Context context){
        this.mContext = context;
        EventBus.getDefault().register(this);
    }

    @Override
    public void onRestClientExceptionThrown(RestClientException e) {
        String errorMessage = null;
       if(e.getCause() instanceof SocketException){
            errorMessage = "can't resolve host";
       }else if(e.getCause() instanceof SocketTimeoutException){
           errorMessage = "socket time out";
       }else if(e.getCause() instanceof HttpStatusCodeException){
           HttpStatusCodeException s = ((HttpStatusCodeException)e.getCause());
           errorMessage = "StatusCode : " + s.getStatusCode() + "StatusText : " + s.getStatusText();
       }else{
           errorMessage = e.getMessage();
       }
        EventBus.getDefault().post(errorMessage);
    }

    @UiThread
    public void onEvent(Object event){
        Log.d(TAG,"&&&&&&&&&&& = " + event.toString());
        Toast.makeText(mContext, event.toString(), Toast.LENGTH_LONG).show();
    }

}
