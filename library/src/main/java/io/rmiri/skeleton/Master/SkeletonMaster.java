package io.rmiri.skeleton.Master;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import io.rmiri.skeleton.R;
import io.rmiri.skeleton.utils.CLog;
import io.rmiri.skeleton.utils.ConverterUnitUtil;


/**
 * Created by Rasoul Miri on 8/20/17.
 */
public class SkeletonMaster extends RelativeLayout {

    protected SkeletonAttribute skeletonAttribute;
    public int position = 0;

    public SkeletonMaster(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public SkeletonMaster(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SkeletonMaster(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SkeletonMaster(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void init(Context context, @Nullable AttributeSet attrs) {


        if (isInEditMode())
            return;

        CLog.i("SkeletonMaster init  " + position);

        // Attribute
        skeletonAttribute = new SkeletonAttribute();
        if (attrs != null) {

            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Skeleton);

            skeletonAttribute.setShowSkeleton(typedArray.getBoolean(R.styleable.Skeleton_SK_isShowSkeleton, true));
            skeletonAttribute.setAutoStartAnimation(typedArray.getBoolean(R.styleable.Skeleton_SK_autoStartAnimation, true));
            skeletonAttribute.setHoldTouchEventsFromChildren(typedArray.getBoolean(R.styleable.Skeleton_SK_isHoldTouchEventsFromChildren, false));
            skeletonAttribute.setColorBackgroundMain(typedArray.getColor(R.styleable.Skeleton_SK_backgroundMainColor, SkeletonAttribute.DEFAULT_COLOR_BACKGROUND_MAIN));
            skeletonAttribute.setColorHighLight(typedArray.getColor(R.styleable.Skeleton_SK_highLightColor, SkeletonAttribute.DEFAULT_COLOR_HIGHLIGHT_GRADIENT));
            skeletonAttribute.setColorBackgroundViews(typedArray.getColor(R.styleable.Skeleton_SK_BackgroundViewsColor, SkeletonAttribute.DEFAULT_COLOR_BACKGROUND_VIEWS));
            skeletonAttribute.setAnimationDuration(typedArray.getInt(R.styleable.Skeleton_SK_animationDuration, SkeletonAttribute.DEFAULT_ANIMATION_DURATION));
            skeletonAttribute.setAnimationDirection(typedArray.getInt(R.styleable.Skeleton_SK_animationDirection, SkeletonAttribute.DEFAULT_ANIMATION_DIRECTION));
            skeletonAttribute.setAnimationNormalType(typedArray.getInt(R.styleable.Skeleton_SK_animationNormalType, SkeletonAttribute.DEFAULT_ANIMATION_TYPE));
            skeletonAttribute.setAnimationFinishType(typedArray.getInt(R.styleable.Skeleton_SK_animationFinishType, SkeletonAttribute.DEFAULT_ANIMATION_TYPE));
            skeletonAttribute.setShapeType(typedArray.getInt(R.styleable.Skeleton_SK_shapeType, SkeletonAttribute.DEFAULT_SHAPE_TYPE));

            // Corner Radius
            skeletonAttribute.setCornerRadius(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_cornerRadius, SkeletonAttribute.DEFAULT_CORNER_RADIUS));
            skeletonAttribute.setCornerRadiusTopLeft(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_cornerRadiusTopLeft, SkeletonAttribute.DEFAULT_CORNER_RADIUS));
            skeletonAttribute.setCornerRadiusTopRight(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_cornerRadiusTopRight, SkeletonAttribute.DEFAULT_CORNER_RADIUS));
            skeletonAttribute.setCornerRadiusBottomLeft(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_cornerRadiusBottomLeft, SkeletonAttribute.DEFAULT_CORNER_RADIUS));
            skeletonAttribute.setCornerRadiusBottomLRight(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_cornerRadiusBottomLRight, SkeletonAttribute.DEFAULT_CORNER_RADIUS));

            // Padding
            skeletonAttribute.setPadding(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_padding, SkeletonAttribute.DEFAULT_PADDING));
            skeletonAttribute.setPaddingTop(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_paddingTop, SkeletonAttribute.DEFAULT_PADDING));
            skeletonAttribute.setPaddingLeft(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_paddingLeft, SkeletonAttribute.DEFAULT_PADDING));
            skeletonAttribute.setPaddingBottom(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_paddingBottom, SkeletonAttribute.DEFAULT_PADDING));
            skeletonAttribute.setPaddingRight(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_paddingRight, SkeletonAttribute.DEFAULT_PADDING));

            // Special attrs shape type text
            if (skeletonAttribute.getShapeType() == SkeletonAttribute.SHAPE_TYPE_TEXT) {
                skeletonAttribute.setTextShapeLineNumber(typedArray.getInt(R.styleable.Skeleton_SK_textLineNumber, SkeletonAttribute.DEFAULT_TEXT_SHAPE_LINE));
                skeletonAttribute.setTextShapeLineLastWidth(typedArray.getInt(R.styleable.Skeleton_SK_textLineLastWidth, SkeletonAttribute.DEFAULT_TEXT_SHAPE_LAST_LINE_WIDTH));
                skeletonAttribute.setTextShapeLineHeight(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_textLineHeight, (int) ConverterUnitUtil.dpToPx(getContext(), SkeletonAttribute.DEFAULT_TEXT_SHAPE_LINE_HEIGHT)));
                skeletonAttribute.setTextShapeLineSpaceVertical(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_textLineSpaceVertical, (int) ConverterUnitUtil.dpToPx(getContext(), SkeletonAttribute.DEFAULT_TEXT_SHAPE_LINE_SPACE_VERTICAL)));
            }

            typedArray.recycle();
        }




    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        /* Cancel clickable from children layout
           isHoldTouchEventsFromChildren default is true, after finish animation is change to false */
        return skeletonAttribute.isHoldTouchEventsFromChildren() || super.onInterceptTouchEvent(motionEvent);
    }


    public ArrayList<View> getAllChildren(View v) {

        CLog.i("SkeletonMaster getAllChildren " + position);

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup viewGroup = (ViewGroup) v;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            View child = viewGroup.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }

        return result;
    }


    //==============================================================================================
    /* Getter and setter */

    public SkeletonAttribute getSkeletonAttribute() {
        return skeletonAttribute;
    }

    public void setSkeletonAttribute(SkeletonAttribute skeletonAttribute) {
        this.skeletonAttribute = skeletonAttribute;
    }

    public boolean isHoldTouchEventsFromChildren() {
        return skeletonAttribute.isHoldTouchEventsFromChildren();
    }

    public void setHoldTouchEventsFromChildren(boolean isHoldTouchEventsFromChildren) {
        skeletonAttribute.setHoldTouchEventsFromChildren(isHoldTouchEventsFromChildren);
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


}
