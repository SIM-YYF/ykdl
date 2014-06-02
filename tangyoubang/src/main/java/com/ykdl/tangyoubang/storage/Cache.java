package com.ykdl.tangyoubang.storage;

import android.content.Context;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.Serializable;

/**
 * Created by admin on 2014/6/2.
 */
@EBean(scope = EBean.Scope.Singleton)
public class Cache {

    public static enum CacheKey {
        CAPTCHA("captcha"),
        USER("user");

        public String value;
        CacheKey(String value) {
            this.value = value;
        }
    };

    @Bean
    TybDB db;


    public void put(Context context, CacheKey key, Serializable ser) {
        db.open(context).put(key.value, ser).close();
    }

    public <T extends Serializable> T get(Context context, CacheKey key, Class<T> clazz) {
        T t = null;
        db = db.open(context);
        if(db.exists(key.value)) {
            t = db.open(context).get(key.value, clazz);
        }
        db.close();
        return t;
    }


}
