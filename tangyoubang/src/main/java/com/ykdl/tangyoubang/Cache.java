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
        //        try {
//             DB snappydb = DBFactory.open(this);
//            snappydb.put("name", "Jack Reacher");
//            snappydb.putInt("age", 42);
//            snappydb.putBoolean("single", true);
//            snappydb.put("books", new String[]{"One Shot", "Tripwire", "61 Hours"});
//
//            name   =  snappydb.get("name");
//            int      age    =  snappydb.getInt("age");
//            boolean  single =  snappydb.getBoolean("single");
//
//            books = snappydb.getArray("books", String.class);
//
//            snappydb.close();
//
//        } catch (SnappydbException e) {
//        }
    }
}
