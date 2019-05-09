package io.rmiri.skeleton.utils;

import android.graphics.Color;

/**
 * Created by Rasoul Miri on 8/22/17.
 */

public class ColorUtils {

    public static int[] generateColorTransparent010(int colorInner, int colorOut) {
        int colorOutTransparent = convertColorToTransparent(colorOut, 0.0f);
        return new int[]{colorOutTransparent, colorInner, colorOutTransparent};
    }

    public static int[] generateColorTransparent011(int colorInner, int colorOut) {
        int colorOutTransparent = convertColorToTransparent(colorOut, 0.0f);
        return new int[]{colorOutTransparent, colorInner, colorOut};
    }

    public static int convertColorToTransparent(int color, float ratio) {
        int newColor ;
        int alpha = Math.round(Color.alpha(color) * ratio);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        newColor = Color.argb(alpha, r, g, b);
        return newColor;
    }
}
