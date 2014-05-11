package com.ykdl.tangyoubang;

import android.app.Application;

import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.EBean;

import de.greenrobot.event.EventBus;

/**
 * Created by admin on 2014/5/10.
 */
@EApplication
public class TybApplication extends Application {

   public final static EventBus BUS = new EventBus();
    @Override
    public void onCreate() {
        super.onCreate();
    }


}
