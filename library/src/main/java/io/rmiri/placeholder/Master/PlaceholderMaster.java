package io.rmiri.placeholder.Master;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import io.rmiri.placeholder.R;
import io.rmiri.placeholder.utils.CLog;
import io.rmiri.placeholder.utils.ConverterUnitUtil;


/**
 * Created by Rasoul Miri on 8/20/17.
 */
public class PlaceholderMaster extends RelativeLayout {

    protected PlaceholderAttribute placeholderAttribute;

    public int postion = 0;

    public PlaceholderMaster(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public PlaceholderMaster(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PlaceholderMaster(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlaceholderMaster(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void init(Context context, @Nullable AttributeSet attrs) {


        if (isInEditMode())
            return;

        CLog.i("PlaceholderMaster init  " + postion);

        //Attribute
        placeholderAttribute = new PlaceholderAttribute();
        if (attrs != null) {

            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Placeholder);

            placeholderAttribute.setShowPlaceHolder(typedArray.getBoolean(R.styleable.Placeholder_PH_isShowPlaceHolder, true));
            placeholderAttribute.setAutoStartAnimation(typedArray.getBoolean(R.styleable.Placeholder_PH_autoStartAnimation, true));
            placeholderAttribute.setHoldTouchEventsFromChildren(typedArray.getBoolean(R.styleable.Placeholder_PH_isHoldTouchEventsFromChildren, true));
            placeholderAttribute.setColorBackgroundMain(typedArray.getColor(R.styleable.Placeholder_PH_backgroundMainColor, PlaceholderAttribute.DEFAULT_COLOR_BACKGROUND_MAIN));
            placeholderAttribute.setColorHighLight(typedArray.getColor(R.styleable.Placeholder_PH_highLightColor, PlaceholderAttribute.DEFAULT_COLOR_HIGHLIGHT_GRADIENT));
            placeholderAttribute.setColorBackgroundViews(typedArray.getColor(R.styleable.Placeholder_PH_BackgroundViewsColor, PlaceholderAttribute.DEFAULT_COLOR_BACKGROUND_VIEWS));
            placeholderAttribute.setAnimationDuration(typedArray.getInt(R.styleable.Placeholder_PH_animationDuration, PlaceholderAttribute.DEFAULT_ANIMATION_DURATION));
            placeholderAttribute.setAnimationDirection(typedArray.getInt(R.styleable.Placeholder_PH_animationDirection, PlaceholderAttribute.DEFAULT_ANIMATION_DIRECTION));
            placeholderAttribute.setAnimationNormalType(typedArray.getInt(R.styleable.Placeholder_PH_animationNormalType, PlaceholderAttribute.DEFAULT_ANIMATION_TYPE));
            placeholderAttribute.setAnimationFinishType(typedArray.getInt(R.styleable.Placeholder_PH_animationFinishType, PlaceholderAttribute.DEFAULT_ANIMATION_TYPE));
            placeholderAttribute.setShapeType(typedArray.getInt(R.styleable.Placeholder_PH_shapeType, PlaceholderAttribute.DEFAULT_SHAPE_TYPE));

            //corner Radius
            placeholderAttribute.setCornerRadius(typedArray.getDimensionPixelSize(R.styleable.Placeholder_PH_cornerRadius, PlaceholderAttribute.DEFAULT_CORNER_RADIUS));
            placeholderAttribute.setCornerRadiusTopLeft(typedArray.getDimensionPixelSize(R.styleable.Placeholder_PH_cornerRadiusTopLeft, PlaceholderAttribute.DEFAULT_CORNER_RADIUS));
            placeholderAttribute.setCornerRadiusTopRight(typedArray.getDimensionPixelSize(R.styleable.Placeholder_PH_cornerRadiusTopRight, PlaceholderAttribute.DEFAULT_CORNER_RADIUS));
            placeholderAttribute.setCornerRadiusBottomLeft(typedArray.getDimensionPixelSize(R.styleable.Placeholder_PH_cornerRadiusBottomLeft, PlaceholderAttribute.DEFAULT_CORNER_RADIUS));
            placeholderAttribute.setCornerRadiusBottomLRight(typedArray.getDimensionPixelSize(R.styleable.Placeholder_PH_cornerRadiusBottomLRight, PlaceholderAttribute.DEFAULT_CORNER_RADIUS));

            //padding
            placeholderAttribute.setPadding(typedArray.getDimensionPixelSize(R.styleable.Placeholder_PH_padding, PlaceholderAttribute.DEFAULT_PADDING));
            placeholderAttribute.setPaddingTop(typedArray.getDimensionPixelSize(R.styleable.Placeholder_PH_paddingTop, PlaceholderAttribute.DEFAULT_PADDING));
            placeholderAttribute.setPaddingLeft(typedArray.getDimensionPixelSize(R.styleable.Placeholder_PH_paddingLeft, PlaceholderAttribute.DEFAULT_PADDING));
            placeholderAttribute.setPaddingBottom(typedArray.getDimensionPixelSize(R.styleable.Placeholder_PH_paddingBottom, PlaceholderAttribute.DEFAULT_PADDING));
            placeholderAttribute.setPaddingRight(typedArray.getDimensionPixelSize(R.styleable.Placeholder_PH_paddingRight, PlaceholderAttribute.DEFAULT_PADDING));

            //special attrs shape type text
            if (placeholderAttribute.getShapeType() == PlaceholderAttribute.SHAPE_TYPE_TEXT) {
                placeholderAttribute.setTextShapeLineNumber(typedArray.getInt(R.styleable.Placeholder_PH_textLineNumber, PlaceholderAttribute.DEFAULT_TEXT_SHAPE_LINE));
                placeholderAttribute.setTextShapeLineLastWidth(typedArray.getInt(R.styleable.Placeholder_PH_textLineLastWidth, PlaceholderAttribute.DEFAULT_TEXT_SHAPE_LAST_LINE_WIDTH));
                placeholderAttribute.setTextShapeLineHeight(typedArray.getDimensionPixelSize(R.styleable.Placeholder_PH_textLineHeight, (int) ConverterUnitUtil.dpToPx(getContext(), PlaceholderAttribute.DEFAULT_TEXT_SHAPE_LINE_HEIGHT)));
                placeholderAttribute.setTextShapeLineSpaceVertical(typedArray.getDimensionPixelSize(R.styleable.Placeholder_PH_textLineSpaceVertical, (int) ConverterUnitUtil.dpToPx(getContext(), PlaceholderAttribute.DEFAULT_TEXT_SHAPE_LINE_SPACE_VERTICAL)));
            }

            typedArray.recycle();
        }

        //set Clickable true for detect onInterceptTouchEvent 
        setClickable(true);


    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        /* cancel clickable from children layout
           isHoldTouchEventsFromChildren default is true, after finish animation is change to false */
        CLog.i("PlaceholderMaster onInterceptTouchEvent  " + postion);
        return placeholderAttribute.isHoldTouchEventsFromChildren() || super.onInterceptTouchEvent(motionEvent);
    }


    public ArrayList<View> getAllChildren(View v) {

        CLog.i("PlaceholderMaster getAllChildren " + postion);

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
    /* getter and setter */

    public PlaceholderAttribute getPlaceholderAttribute() {
        return placeholderAttribute;
    }

    public void setPlaceholderAttribute(PlaceholderAttribute placeholderAttribute) {
        this.placeholderAttribute = placeholderAttribute;
    }

    public boolean isHoldTouchEventsFromChildren() {
        return placeholderAttribute.isHoldTouchEventsFromChildren();
    }

    public void setHoldTouchEventsFromChildren(boolean isHoldTouchEventsFromChildren) {
        placeholderAttribute.setHoldTouchEventsFromChildren(isHoldTouchEventsFromChildren);
    }


    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }
}
