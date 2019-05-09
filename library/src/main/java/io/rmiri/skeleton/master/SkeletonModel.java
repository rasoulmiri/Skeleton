package io.rmiri.skeleton.master;

import android.graphics.Color;
import android.graphics.RectF;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Rasoul Miri on 2019-05-08.
 */
public class SkeletonModel {

    // views for star and end skeleton
    private View childView;
    private View startView;
    private View endView;

    // Fix value for width and height
    private float customWidth = Float.MIN_VALUE;
    private float customHeight = Float.MIN_VALUE;

    private boolean isMatchViewBoolean = false;

    // Variable for draw
    private RectF rectF;
    private ArrayList<Float> cornerRadiusFloatArray;

    private float x1 = 0f;
    private float y1 = 0f;
    private float x2 = 0f;
    private float y2 = 0f;

    private boolean isShowSkeleton = true;
    private boolean isAutoStartAnimation = true;
    private boolean isHoldTouchEventsFromChildren = true;
    private int shapeType = SHAPE_TYPE_RECT;// Shape background type

    // Color
    private int colorBackgroundMain = DEFAULT_COLOR_BACKGROUND_MAIN;
    private int colorBackgroundViews = DEFAULT_COLOR_BACKGROUND_VIEWS;
    private int colorHighLight = DEFAULT_COLOR_HIGHLIGHT_GRADIENT;

    // Animation
    private long animationDuration = DEFAULT_ANIMATION_DURATION;
    private int animationDirection = ANIMATION_DIRECTION_LTR;
    private int animationNormalType = ANIMATION_TYPE_GRADIENT;
    private int animationFinishType = ANIMATION_TYPE_GRADIENT;


    // CornerRadius
    private int cornerRadius = Integer.MIN_VALUE;
    private int cornerRadiusTopLeft = Integer.MIN_VALUE;
    private int cornerRadiusTopRight = Integer.MIN_VALUE;
    private int cornerRadiusBottomLeft = Integer.MIN_VALUE;
    private int cornerRadiusBottomLRight = Integer.MIN_VALUE;

    // Padding
    private float padding = Float.MIN_VALUE;
    private float paddingTop = Float.MIN_VALUE;
    private float paddingRight = Float.MIN_VALUE;
    private float paddingBottom = Float.MIN_VALUE;
    private float paddingLeft = Float.MIN_VALUE;

    // Special attrs shape type text
    private int textShapeLineNumber = DEFAULT_TEXT_SHAPE_LINE;
    private int textShapeLineLastWidth = DEFAULT_TEXT_SHAPE_LAST_LINE_WIDTH;
    private int textShapeLineHeight = DEFAULT_TEXT_SHAPE_LINE_HEIGHT;
    private int textShapeLineSpaceVertical = DEFAULT_TEXT_SHAPE_LINE_SPACE_VERTICAL;

    /* Enum*/
    public static final int SHAPE_TYPE_RECT = 1;
    public static final int SHAPE_TYPE_OVAL = 2;
    public static final int SHAPE_TYPE_TEXT = 3;

    public static final int ANIMATION_DIRECTION_LTR = 1;
    public static final int ANIMATION_DIRECTION_RTL = 2;
    public static final int ANIMATION_DIRECTION_TTB = 3;
    public static final int ANIMATION_DIRECTION_BTT = 4;

    public static final int ANIMATION_TYPE_NON = 0;
    public static final int ANIMATION_TYPE_ALPHA = 1;
    public static final int ANIMATION_TYPE_GRADIENT = 2;

    public static final int TEXT_SHAPE_LINE_LAST_WIDTH_FULL = 1;
    public static final int TEXT_SHAPE_LINE_LAST_WIDTH_THREE_QUARTERS = 2;
    public static final int TEXT_SHAPE_LINE_LAST_WIDTH_HALF = 3;
    public static final int TEXT_SHAPE_LINE_LAST_WIDTH_QUARTER = 4;


    /* Default value*/
    public static final int DEFAULT_COLOR_BACKGROUND_MAIN = android.R.color.transparent;
    public static final int DEFAULT_COLOR_BACKGROUND_VIEWS = Color.parseColor("#EEEEEE");
    public static final int DEFAULT_COLOR_HIGHLIGHT_GRADIENT = Color.parseColor("#DEDEDE");

    public static final int DEFAULT_ANIMATION_DURATION = 1000;
    public static final int DEFAULT_ANIMATION_DIRECTION = ANIMATION_DIRECTION_LTR;
    public static final int DEFAULT_ANIMATION_TYPE = ANIMATION_TYPE_GRADIENT;

    public static final int DEFAULT_SHAPE_TYPE = SHAPE_TYPE_RECT;

    public static final int DEFAULT_CORNER_RADIUS = Integer.MIN_VALUE;
    public static final int DEFAULT_PADDING = Integer.MIN_VALUE;

    public static final int DEFAULT_TEXT_SHAPE_LINE = 3;
    public static final int DEFAULT_TEXT_SHAPE_LAST_LINE_WIDTH = TEXT_SHAPE_LINE_LAST_WIDTH_THREE_QUARTERS;
    public static final int DEFAULT_TEXT_SHAPE_LINE_HEIGHT = 24;
    public static final int DEFAULT_TEXT_SHAPE_LINE_SPACE_VERTICAL = 4;



    public SkeletonModel(){

    }

    public SkeletonModel(View childView, View startView, View endView, float customWidth, float customHeight, boolean isMatchViewBoolean, RectF rectF, ArrayList<Float> cornerRadiusFloatArray, float x1, float y1, float x2, float y2, boolean isShowSkeleton, boolean isAutoStartAnimation, boolean isHoldTouchEventsFromChildren, int shapeType, int colorBackgroundMain, int colorBackgroundViews, int colorHighLight, long animationDuration, int animationDirection, int animationNormalType, int animationFinishType, int cornerRadius, int cornerRadiusTopLeft, int cornerRadiusTopRight, int cornerRadiusBottomLeft, int cornerRadiusBottomLRight, float padding, float paddingTop, float paddingRight, float paddingBottom, float paddingLeft, int textShapeLineNumber, int textShapeLineLastWidth, int textShapeLineHeight, int textShapeLineSpaceVertical) {
        this.childView = childView;
        this.startView = startView;
        this.endView = endView;
        this.customWidth = customWidth;
        this.customHeight = customHeight;
        this.isMatchViewBoolean = isMatchViewBoolean;
        this.rectF = rectF;
        this.cornerRadiusFloatArray = cornerRadiusFloatArray;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.isShowSkeleton = isShowSkeleton;
        this.isAutoStartAnimation = isAutoStartAnimation;
        this.isHoldTouchEventsFromChildren = isHoldTouchEventsFromChildren;
        this.shapeType = shapeType;
        this.colorBackgroundMain = colorBackgroundMain;
        this.colorBackgroundViews = colorBackgroundViews;
        this.colorHighLight = colorHighLight;
        this.animationDuration = animationDuration;
        this.animationDirection = animationDirection;
        this.animationNormalType = animationNormalType;
        this.animationFinishType = animationFinishType;
        this.cornerRadius = cornerRadius;
        this.cornerRadiusTopLeft = cornerRadiusTopLeft;
        this.cornerRadiusTopRight = cornerRadiusTopRight;
        this.cornerRadiusBottomLeft = cornerRadiusBottomLeft;
        this.cornerRadiusBottomLRight = cornerRadiusBottomLRight;
        this.padding = padding;
        this.paddingTop = paddingTop;
        this.paddingRight = paddingRight;
        this.paddingBottom = paddingBottom;
        this.paddingLeft = paddingLeft;
        this.textShapeLineNumber = textShapeLineNumber;
        this.textShapeLineLastWidth = textShapeLineLastWidth;
        this.textShapeLineHeight = textShapeLineHeight;
        this.textShapeLineSpaceVertical = textShapeLineSpaceVertical;
    }

    public View getChildView() {
        return childView;
    }

    public void setChildView(View childView) {
        this.childView = childView;
    }

    public View getStartView() {
        return startView;
    }

    public void setStartView(View startView) {
        this.startView = startView;
    }

    public View getEndView() {
        return endView;
    }

    public void setEndView(View endView) {
        this.endView = endView;
    }

    public float getCustomWidth() {
        return customWidth;
    }

    public void setCustomWidth(float customWidth) {
        this.customWidth = customWidth;
    }

    public float getCustomHeight() {
        return customHeight;
    }

    public void setCustomHeight(float customHeight) {
        this.customHeight = customHeight;
    }

    public boolean isMatchViewBoolean() {
        return isMatchViewBoolean;
    }

    public void setMatchViewBoolean(boolean matchViewBoolean) {
        isMatchViewBoolean = matchViewBoolean;
    }

    public RectF getRectF() {
        return rectF;
    }

    public void setRectF(RectF rectF) {
        this.rectF = rectF;
    }

    public ArrayList<Float> getCornerRadiusFloatArray() {
        return cornerRadiusFloatArray;
    }

    public void setCornerRadiusFloatArray(ArrayList<Float> cornerRadiusFloatArray) {
        this.cornerRadiusFloatArray = cornerRadiusFloatArray;
    }

    public float getX1() {
        return x1;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public float getY1() {
        return y1;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public float getX2() {
        return x2;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public float getY2() {
        return y2;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    public boolean isShowSkeleton() {
        return isShowSkeleton;
    }

    public void setShowSkeleton(boolean showSkeleton) {
        isShowSkeleton = showSkeleton;
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

    public int getColorHighLight() {
        return colorHighLight;
    }

    public void setColorHighLight(int colorHighLight) {
        this.colorHighLight = colorHighLight;
    }

    public long getAnimationDuration() {
        return animationDuration;
    }

    public void setAnimationDuration(long animationDuration) {
        this.animationDuration = animationDuration;
    }

    public int getAnimationDirection() {
        return animationDirection;
    }

    public void setAnimationDirection(int animationDirection) {
        this.animationDirection = animationDirection;
    }

    public int getAnimationNormalType() {
        return animationNormalType;
    }

    public void setAnimationNormalType(int animationNormalType) {
        this.animationNormalType = animationNormalType;
    }

    public int getAnimationFinishType() {
        return animationFinishType;
    }

    public void setAnimationFinishType(int animationFinishType) {
        this.animationFinishType = animationFinishType;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public int getCornerRadiusTopLeft() {
        return cornerRadiusTopLeft;
    }

    public void setCornerRadiusTopLeft(int cornerRadiusTopLeft) {
        this.cornerRadiusTopLeft = cornerRadiusTopLeft;
    }

    public int getCornerRadiusTopRight() {
        return cornerRadiusTopRight;
    }

    public void setCornerRadiusTopRight(int cornerRadiusTopRight) {
        this.cornerRadiusTopRight = cornerRadiusTopRight;
    }

    public int getCornerRadiusBottomLeft() {
        return cornerRadiusBottomLeft;
    }

    public void setCornerRadiusBottomLeft(int cornerRadiusBottomLeft) {
        this.cornerRadiusBottomLeft = cornerRadiusBottomLeft;
    }

    public int getCornerRadiusBottomLRight() {
        return cornerRadiusBottomLRight;
    }

    public void setCornerRadiusBottomLRight(int cornerRadiusBottomLRight) {
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

    public int getTextShapeLineNumber() {
        return textShapeLineNumber;
    }

    public void setTextShapeLineNumber(int textShapeLineNumber) {
        this.textShapeLineNumber = textShapeLineNumber;
    }

    public int getTextShapeLineLastWidth() {
        return textShapeLineLastWidth;
    }

    public void setTextShapeLineLastWidth(int textShapeLineLastWidth) {
        this.textShapeLineLastWidth = textShapeLineLastWidth;
    }

    public int getTextShapeLineHeight() {
        return textShapeLineHeight;
    }

    public void setTextShapeLineHeight(int textShapeLineHeight) {
        this.textShapeLineHeight = textShapeLineHeight;
    }

    public int getTextShapeLineSpaceVertical() {
        return textShapeLineSpaceVertical;
    }

    public void setTextShapeLineSpaceVertical(int textShapeLineSpaceVertical) {
        this.textShapeLineSpaceVertical = textShapeLineSpaceVertical;
    }
}


