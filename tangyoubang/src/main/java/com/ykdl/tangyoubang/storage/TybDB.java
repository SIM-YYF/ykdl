package com.ykdl.tangyoubang.storage;

import android.content.Context;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import org.androidannotations.annotations.EBean;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by yuanwenfei on 2014/5/28.
 * 数据库存储
 */
@EBean(scope = EBean.Scope.Singleton)
public class TybDB {
    private static final String DB_NAME = "tybdb";
    private static com.snappydb.DB db;
    public  TybDB open(Context context){
        try {
            db= DBFactory.open(context,DB_NAME);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return this;
    }

    //***********************
    //*      CREATE
    //***********************
    public TybDB put (String key, byte[] data) {
        try {

            db.put(key, data);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return this;
    }

    public TybDB put (String key, String value) {

        try {
            db.put(key, value);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return this;

    }

    public TybDB put (String key, Serializable value){

        try {
            db.put(key, value);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return this;
    }

    public TybDB put (String key, Serializable [] value) {

        try {
            db.put(key, value);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return this;
    }

    public TybDB putInt (String key, int val){
        try {
            db.put(key, val);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return this;
    }

    public TybDB putShort (String key, short val){

        try {
            db.put(key, val);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return this;
    }

    public TybDB putBoolean (String key, boolean val) {

        try {
            db.put(key, val);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return this;
    }

    public TybDB putDouble (String key, double val) {
        try {
            db.put(key, val);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return this;
    }

    public TybDB putFloat (String key, float val){
        try {
            db.put(key, val);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return this;
    }

    public TybDB putLong (String key, long val){
        try {
            db.put(key, val);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return this;
    }
    //***********************
    //*      RETRIEVE
    //***********************
    public String get(String key){
        try {
            return db.get(key);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return null;
    }

    public byte[] getBytes(String key) {

        try {
            return db.getBytes(key);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return null;
    }

    public <T extends Serializable> T get(String key, Class<T> className){
        try {
            return db.get(key, className);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return null;
    }

    public <T extends Serializable> T[] getArray (String key, Class<T> className) {
        try {
            return db.getArray(key, className);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return null;
    }

    public short getShort(String key) {
        try {
            return db.getShort(key);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return -1;
    }

    public int getInt(String key){
        try {
            return db.getInt(key);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return -1;
    }

    public boolean getBoolean(String key){
        try {
            return db.getBoolean(key);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return false;
    }

    public double getDouble(String key){
        try {
            return db.getDouble(key);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return -1;
    }

    public long getLong(String key)  {
        try {
            return db.getLong(key);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return -1;
    }

    public float getFloat(String key) {
        try {
            return db.getFloat(key);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return -1;
    }

    //****************************
    //*      KEYS OPERATIONS
    //****************************
    public boolean exists (String key) {
        try {
            return db.exists(key);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return false;

    }
    //***********************
    //*      DELETE
    //***********************

    public TybDB del (String key){

        try {
             db.del(key);
        } catch (SnappydbException e) {
            close();
            e.printStackTrace();
        }
        return this;
    }

    public void close(){
        try {
            db.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }
}
