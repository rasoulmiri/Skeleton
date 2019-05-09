package io.rmiri.skeleton.utils;

import android.util.Log;

import io.rmiri.skeleton.BuildConfig;


/**
 * Created by Rasoul Miri on 8/24/17.
 */

public class CLog {

    private static final String Tag = "Skeleton";

    public static void i(String message) {
        if (BuildConfig.DEBUG) {
            Log.i(Tag, message);
        }
    }
}
