package io.rmiri.placeholder.utils;

import android.util.Log;

import io.rmiri.placeholder.BuildConfig;

/**
 * Created by Rasoul Miri on 8/24/17.
 */

public class CLog {

    private static String Tag = "PlaceHolder";

    public static void i(String message) {
        if (BuildConfig.DEBUG) {
            Log.i(Tag, message);
        }
    }
}
