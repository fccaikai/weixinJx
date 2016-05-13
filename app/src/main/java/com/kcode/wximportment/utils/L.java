package com.kcode.wximportment.utils;

import android.util.Log;

import com.kcode.wximportment.BuildConfig;

/**
 * Created by caik on 16/5/13.
 */
public class L {
    public static void i(String tag,String msg){
        if(BuildConfig.DEBUG){
            Log.i(tag, msg);
        }
    }

    public static void e(String tag,String msg){
        if(BuildConfig.DEBUG){
            Log.e(tag, msg);
        }
    }
}
