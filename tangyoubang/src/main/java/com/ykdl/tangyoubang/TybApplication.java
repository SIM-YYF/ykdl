package com.ykdl.tangyoubang;

import android.app.Application;

import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.EBean;
import org.androidannotations.api.BackgroundExecutor;

import java.util.concurrent.Executors;

import de.greenrobot.event.EventBus;

/**
 * Created by admin on 2014/5/10.
 */
@EApplication
public class TybApplication extends Application {
   public final static EventBus BUS = new EventBus();
    static {//初始化后台核心线程池
        BackgroundExecutor.setExecutor(Executors.newScheduledThreadPool(5));
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
