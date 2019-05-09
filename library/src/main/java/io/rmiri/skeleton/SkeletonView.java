package io.rmiri.skeleton;

import android.content.Context;
import android.os.Build;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StyleRes;
import android.util.AttributeSet;

import io.rmiri.skeleton.master.SkeletonMaster;


/**
 * Created by Rasoul Miri on 8/8/17.
 */

public class SkeletonView extends SkeletonMaster {

    public SkeletonView(@NonNull Context context) {
        super(context);
    }

    public SkeletonView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SkeletonView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SkeletonView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}