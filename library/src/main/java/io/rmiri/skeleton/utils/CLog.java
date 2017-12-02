package io.rmiri.skeleton.utils;

import android.util.Log;


/**
 * Created by Rasoul Miri on 8/24/17.
 */

public class CLog {

    private static String Tag = "Skeleton";

    public static void i(String message) {
//        if (BuildConfig.DEBUG) {
            Log.i(Tag, message);
//        }
    }
}
