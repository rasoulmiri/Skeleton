package io.rmiri.skeleton.master;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StyleRes;
import android.util.AttributeSet;
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

    protected SkeletonModel skeletonModel;
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
        skeletonModel = new SkeletonModel();
        if (attrs != null) {

            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Skeleton);

            skeletonModel.setShowSkeleton(typedArray.getBoolean(R.styleable.Skeleton_SK_isShowSkeleton, true));
            skeletonModel.setAutoStartAnimation(typedArray.getBoolean(R.styleable.Skeleton_SK_autoStartAnimation, true));
            skeletonModel.setHoldTouchEventsFromChildren(typedArray.getBoolean(R.styleable.Skeleton_SK_isHoldTouchEventsFromChildren, false));
            skeletonModel.setColorBackgroundMain(typedArray.getColor(R.styleable.Skeleton_SK_backgroundMainColor, SkeletonModel.DEFAULT_COLOR_BACKGROUND_MAIN));
            skeletonModel.setColorHighLight(typedArray.getColor(R.styleable.Skeleton_SK_highLightColor, SkeletonModel.DEFAULT_COLOR_HIGHLIGHT_GRADIENT));
            skeletonModel.setColorBackgroundViews(typedArray.getColor(R.styleable.Skeleton_SK_BackgroundViewsColor, SkeletonModel.DEFAULT_COLOR_BACKGROUND_VIEWS));
            skeletonModel.setAnimationDuration(typedArray.getInt(R.styleable.Skeleton_SK_animationDuration, SkeletonModel.DEFAULT_ANIMATION_DURATION));
            skeletonModel.setAnimationDirection(typedArray.getInt(R.styleable.Skeleton_SK_animationDirection, SkeletonModel.DEFAULT_ANIMATION_DIRECTION));
            skeletonModel.setAnimationNormalType(typedArray.getInt(R.styleable.Skeleton_SK_animationNormalType, SkeletonModel.DEFAULT_ANIMATION_TYPE));
            skeletonModel.setAnimationFinishType(typedArray.getInt(R.styleable.Skeleton_SK_animationFinishType, SkeletonModel.DEFAULT_ANIMATION_TYPE));
            skeletonModel.setShapeType(typedArray.getInt(R.styleable.Skeleton_SK_shapeType, SkeletonModel.DEFAULT_SHAPE_TYPE));

            // Corner Radius
            skeletonModel.setCornerRadius(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_cornerRadius, SkeletonModel.DEFAULT_CORNER_RADIUS));
            skeletonModel.setCornerRadiusTopLeft(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_cornerRadiusTopLeft, SkeletonModel.DEFAULT_CORNER_RADIUS));
            skeletonModel.setCornerRadiusTopRight(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_cornerRadiusTopRight, SkeletonModel.DEFAULT_CORNER_RADIUS));
            skeletonModel.setCornerRadiusBottomLeft(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_cornerRadiusBottomLeft, SkeletonModel.DEFAULT_CORNER_RADIUS));
            skeletonModel.setCornerRadiusBottomLRight(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_cornerRadiusBottomLRight, SkeletonModel.DEFAULT_CORNER_RADIUS));

            // Padding
            skeletonModel.setPadding(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_padding, SkeletonModel.DEFAULT_PADDING));
            skeletonModel.setPaddingTop(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_paddingTop, SkeletonModel.DEFAULT_PADDING));
            skeletonModel.setPaddingLeft(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_paddingLeft, SkeletonModel.DEFAULT_PADDING));
            skeletonModel.setPaddingBottom(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_paddingBottom, SkeletonModel.DEFAULT_PADDING));
            skeletonModel.setPaddingRight(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_paddingRight, SkeletonModel.DEFAULT_PADDING));

            // Special attrs shape type text
            if (skeletonModel.getShapeType() == SkeletonModel.SHAPE_TYPE_TEXT) {
                skeletonModel.setTextShapeLineNumber(typedArray.getInt(R.styleable.Skeleton_SK_textLineNumber, SkeletonModel.DEFAULT_TEXT_SHAPE_LINE));
                skeletonModel.setTextShapeLineLastWidth(typedArray.getInt(R.styleable.Skeleton_SK_textLineLastWidth, SkeletonModel.DEFAULT_TEXT_SHAPE_LAST_LINE_WIDTH));
                skeletonModel.setTextShapeLineHeight(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_textLineHeight, (int) ConverterUnitUtil.dpToPx(getContext(), SkeletonModel.DEFAULT_TEXT_SHAPE_LINE_HEIGHT)));
                skeletonModel.setTextShapeLineSpaceVertical(typedArray.getDimensionPixelSize(R.styleable.Skeleton_SK_textLineSpaceVertical, (int) ConverterUnitUtil.dpToPx(getContext(), SkeletonModel.DEFAULT_TEXT_SHAPE_LINE_SPACE_VERTICAL)));
            }

            typedArray.recycle();
        }

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        /* Cancel clickable from children layout
           isHoldTouchEventsFromChildren default is true, after finish animation is change to false */
        return skeletonModel.isHoldTouchEventsFromChildren() || super.onInterceptTouchEvent(motionEvent);
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
    /* Getter and Setter */

    public SkeletonModel getSkeletonModel() {
        return skeletonModel;
    }

    public void setSkeletonModel(SkeletonModel skeletonModel) {
        this.skeletonModel = skeletonModel;
    }

    public boolean isHoldTouchEventsFromChildren() {
        return skeletonModel.isHoldTouchEventsFromChildren();
    }

    public void setHoldTouchEventsFromChildren(boolean isHoldTouchEventsFromChildren) {
        skeletonModel.setHoldTouchEventsFromChildren(isHoldTouchEventsFromChildren);
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


}
