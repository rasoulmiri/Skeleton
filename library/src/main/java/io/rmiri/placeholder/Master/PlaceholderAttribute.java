package io.rmiri.placeholder.Master;

import android.graphics.Color;

/**
 * Created by Rasoul Miri on 8/20/17.
 */

public class PlaceholderAttribute {

    private float X1, Y1, X2, Y2;

    private boolean isShowPlaceHolder = true;
    private boolean isAutoStartAnimation = true;
    private boolean isHoldTouchEventsFromChildren = true;

    private int shapeType; //shape background type

    /*color*/
    private int colorBackgroundMain;
    private int colorBackgroundViews;
    private int colorCenterGradient;

    /*animation*/
    private int animationDuration;
    private int animationDirection;
    private int animationFinishType;



    /*cornerRadius*/
    private float cornerRadius;
    private float cornerRadiusTopLeft;
    private float cornerRadiusTopRight;
    private float cornerRadiusBottomLeft;
    private float cornerRadiusBottomLRight;

    /*padding*/
    private float padding;
    private float paddingTop;
    private float paddingRight;
    private float paddingBottom;
    private float paddingLeft;

    /*enum*/
    public static final int SHAPE_TYPE_RECT = 1;
    public static final int SHAPE_TYPE_OVAL = 2;

    public static final int ANIMATION_DIRECTION_LTR = 1;
    public static final int ANIMATION_DIRECTION_RTL = 2;
    public static final int ANIMATION_DIRECTION_TTB = 3;
    public static final int ANIMATION_DIRECTION_BTT = 4;

    public static final int ANIMATION_FINISH_TYPE_NON = 0;
    public static final int ANIMATION_FINISH_TYPE_ALPHA = 1;
    public static final int ANIMATION_FINISH_TYPE_GRADIENT = 2;


    /*default value*/
    public static final int DEFAULT_COLOR_BACKGROUND_MAIN = android.R.color.transparent;
    public static final int DEFAULT_COLOR_BACKGROUND_VIEWS =Color.parseColor("#EEEEEE");
    public static final int DEFAULT_COLOR_CENTER_GRADIENT = Color.parseColor("#DEDEDE");

    public static final int DEFAULT_ANIMATION_DURATION = 1000;
    public static final int DEFAULT_ANIMATION_DIRECTION = ANIMATION_DIRECTION_LTR;
    public static final int DEFAULT_ANIMATION_FINISH_TYPE = ANIMATION_FINISH_TYPE_ALPHA;

    public static final int DEFAULT_SHAPE_TYPE = SHAPE_TYPE_RECT;

    public static final int DEFAULT_CORNER_RADIUS = Integer.MIN_VALUE;
    public static final int DEFAULT_PADDING = Integer.MIN_VALUE;


    public PlaceholderAttribute() {
    }
    //==============================================================================================
    /* getter and setter */

    public float getX1() {
        return X1;
    }

    public void setX1(float x1) {
        X1 = x1;
    }

    public float getY1() {
        return Y1;
    }

    public void setY1(float y1) {
        Y1 = y1;
    }

    public float getX2() {
        return X2;
    }

    public void setX2(float x2) {
        X2 = x2;
    }

    public float getY2() {
        return Y2;
    }

    public void setY2(float y2) {
        Y2 = y2;
    }

    public boolean isShowPlaceHolder() {
        return isShowPlaceHolder;
    }

    public void setShowPlaceHolder(boolean showPlaceHolder) {
        isShowPlaceHolder = showPlaceHolder;
    }

    public boolean isAutoStartAnimation() {
        return isAutoStartAnimation;
    }

    public void setAutoStartAnimation(boolean autoStartAnimation) {
        isAutoStartAnimation = autoStartAnimation;
    }

    public boolean isHoldTouchEventsFromChildren() {
        return isHoldTouchEventsFromChildren;
    }

    public void setHoldTouchEventsFromChildren(boolean holdTouchEventsFromChildren) {
        isHoldTouchEventsFromChildren = holdTouchEventsFromChildren;
    }

    public int getShapeType() {
        return shapeType;
    }

    public void setShapeType(int shapeType) {
        this.shapeType = shapeType;
    }

    public int getColorBackgroundMain() {
        return colorBackgroundMain;
    }

    public void setColorBackgroundMain(int colorBackgroundMain) {
        this.colorBackgroundMain = colorBackgroundMain;
    }

    public int getColorBackgroundViews() {
        return colorBackgroundViews;
    }

    public void setColorBackgroundViews(int colorBackgroundViews) {
        this.colorBackgroundViews = colorBackgroundViews;
    }

    public int getColorCenterGradient() {
        return colorCenterGradient;
    }

    public void setColorCenterGradient(int colorCenterGradient) {
        this.colorCenterGradient = colorCenterGradient;
    }

    public int getAnimationDuration() {
        return animationDuration;
    }

    public void setAnimationDuration(int animationDuration) {
        this.animationDuration = animationDuration;
    }

    public int getAnimationDirection() {
        return animationDirection;
    }

    public void setAnimationDirection(int animationDirection) {
        this.animationDirection = animationDirection;
    }

    public int getAnimationFinishType() {
        return animationFinishType;
    }

    public void setAnimationFinishType(int animationFinishType) {
        this.animationFinishType = animationFinishType;
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public float getCornerRadiusTopLeft() {
        return cornerRadiusTopLeft;
    }

    public void setCornerRadiusTopLeft(float cornerRadiusTopLeft) {
        this.cornerRadiusTopLeft = cornerRadiusTopLeft;
    }

    public float getCornerRadiusTopRight() {
        return cornerRadiusTopRight;
    }

    public void setCornerRadiusTopRight(float cornerRadiusTopRight) {
        this.cornerRadiusTopRight = cornerRadiusTopRight;
    }

    public float getCornerRadiusBottomLeft() {
        return cornerRadiusBottomLeft;
    }

    public void setCornerRadiusBottomLeft(float cornerRadiusBottomLeft) {
        this.cornerRadiusBottomLeft = cornerRadiusBottomLeft;
    }

    public float getCornerRadiusBottomLRight() {
        return cornerRadiusBottomLRight;
    }

    public void setCornerRadiusBottomLRight(float cornerRadiusBottomLRight) {
        this.cornerRadiusBottomLRight = cornerRadiusBottomLRight;
    }

    public float getPadding() {
        return padding;
    }

    public void setPadding(float padding) {
        this.padding = padding;
    }

    public float getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(float paddingTop) {
        this.paddingTop = paddingTop;
    }

    public float getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(float paddingRight) {
        this.paddingRight = paddingRight;
    }

    public float getPaddingBottom() {
        return paddingBottom;
    }

    public void setPaddingBottom(float paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    public float getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(float paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public static int getShapeTypeRect() {
        return SHAPE_TYPE_RECT;
    }

    public static int getShapeTypeOval() {
        return SHAPE_TYPE_OVAL;
    }

    public static int getAnimationDirectionLtr() {
        return ANIMATION_DIRECTION_LTR;
    }

    public static int getAnimationDirectionRtl() {
        return ANIMATION_DIRECTION_RTL;
    }

    public static int getAnimationDirectionTtb() {
        return ANIMATION_DIRECTION_TTB;
    }

    public static int getAnimationDirectionBtt() {
        return ANIMATION_DIRECTION_BTT;
    }

    public static int getAnimationFinishTypeNon() {
        return ANIMATION_FINISH_TYPE_NON;
    }

    public static int getAnimationFinishTypeAlpha() {
        return ANIMATION_FINISH_TYPE_ALPHA;
    }

    public static int getAnimationFinishTypeGradient() {
        return ANIMATION_FINISH_TYPE_GRADIENT;
    }

    public static int getDefaultColorBackgroundMain() {
        return DEFAULT_COLOR_BACKGROUND_MAIN;
    }

    public static int getDefaultColorBackgroundViews() {
        return DEFAULT_COLOR_BACKGROUND_VIEWS;
    }

    public static int getDefaultColorCenterGradient() {
        return DEFAULT_COLOR_CENTER_GRADIENT;
    }

    public static int getDefaultAnimationDuration() {
        return DEFAULT_ANIMATION_DURATION;
    }

    public static int getDefaultAnimationDirection() {
        return DEFAULT_ANIMATION_DIRECTION;
    }

    public static int getDefaultAnimationFinishType() {
        return DEFAULT_ANIMATION_FINISH_TYPE;
    }

    public static int getDefaultShapeType() {
        return DEFAULT_SHAPE_TYPE;
    }

    public static int getDefaultCornerRadius() {
        return DEFAULT_CORNER_RADIUS;
    }

    public static int getDefaultPadding() {
        return DEFAULT_PADDING;
    }
}