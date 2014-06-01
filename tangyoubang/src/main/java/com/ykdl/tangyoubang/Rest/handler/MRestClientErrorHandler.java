package com.ykdl.tangyoubang.Rest.handler;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.api.rest.RestErrorHandler;
import org.apache.http.conn.ConnectTimeoutException;
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
    private static final String TAG = "MRestClientErrorHandler";

    @Bean
    public RestErrorMessage  restErrorMessage;

    public void setErrorHandler(Context context){
        this.mContext = context;
    }
    @AfterInject
    public void init(){
        EventBus.getDefault().register(this);
    }

    @Override
    public void onRestClientExceptionThrown(RestClientException e) {
       if(e.getCause() instanceof SocketException) {
           restErrorMessage.message = "can't resolve host";
       }else if(e.getCause() instanceof ConnectTimeoutException){
           restErrorMessage.message = "connect time out";
       }else if(e.getCause() instanceof SocketTimeoutException){
           restErrorMessage.message = "socket time out";
       }else if(e.getCause() instanceof HttpStatusCodeException) {
           HttpStatusCodeException s = ((HttpStatusCodeException) e.getCause());
           restErrorMessage.message = "StatusCode : " + s.getStatusCode() + "StatusText : " + s.getStatusText();
       }else if(e.getCause() instanceof UnknownHostException){
           restErrorMessage.message = "not network or the IP address of a host could not be determined ";
       }else{
           restErrorMessage.message = e.getMessage();
       }
        EventBus.getDefault().post(restErrorMessage);
    }

    @UiThread
    public void onEvent(RestErrorMessage  errorMessage){
        Log.d(TAG, errorMessage.message);
        Toast.makeText(mContext, errorMessage.message, Toast.LENGTH_LONG).show();
    }

}
