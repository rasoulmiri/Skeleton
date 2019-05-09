package io.rmiri.skeleton.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Rasoul Miri on 8/24/17.
 */

public class ConverterUnitUtil {

    public static float pxToDp(Context context, float pixel) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixel, context.getResources().getDisplayMetrics());
    }

    public static float dpToPx(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int dpToPx(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static float spToPx(Context context,float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

}
