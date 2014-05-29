package com.ykdl.tangyoubang;

import org.androidannotations.annotations.EBean;

import java.util.Objects;

/**
 * Created by yuanwenfei on 2014/5/28.
 */
@EBean
public class Cache {
    public static enum CacheKey { USER, Goods }
//    public <T> T get(CacheKey key, Class<T> returnType) {
//    }
    public void put(CacheKey key, Object value) {

    }
}
