package io.rmiri.skeleton.Master;

import android.graphics.Color;

/**
 * Created by Rasoul Miri on 8/20/17.
 */

public class SkeletonAttribute {

    private float X1, Y1, X2, Y2;

    private boolean isShowSkeleton = true;
    private boolean isAutoStartAnimation = true;
    private boolean isHoldTouchEventsFromChildren = true;

    private int shapeType; // Shape background type

    /* Color*/
    private int colorBackgroundMain;
    private int colorBackgroundViews;
    private int colorHighLight;

    /* Animation*/
    private int animationDuration;
    private int animationDirection;
    private int animationNormalType;
    private int animationFinishType;


    /* CornerRadius*/
    private float cornerRadius;
    private float cornerRadiusTopLeft;
    private float cornerRadiusTopRight;
    private float cornerRadiusBottomLeft;
    private float cornerRadiusBottomLRight;

    /* Padding*/
    private float padding;
    private float paddingTop;
    private float paddingRight;
    private float paddingBottom;
    private float paddingLeft;

    /* Special attrs shape type text*/
    private int textShapeLineNumber;
    private int textShapeLineLastWidth;
    private float textShapeLineHeight;
    private float textShapeLineSpaceVertical;

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

    public static final int DEFAULT_TEXT_SHAPE_LINE =3;
    public static final int DEFAULT_TEXT_SHAPE_LAST_LINE_WIDTH = TEXT_SHAPE_LINE_LAST_WIDTH_THREE_QUARTERS;
    public static final int DEFAULT_TEXT_SHAPE_LINE_HEIGHT = 24;
    public static final int DEFAULT_TEXT_SHAPE_LINE_SPACE_VERTICAL = 4;


    public SkeletonAttribute() {
    }
    //==============================================================================================
    /* Getter and setter */

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

    public float getTextShapeLineHeight() {
        return textShapeLineHeight;
    }

    public void setTextShapeLineHeight(float textShapeLineHeight) {
        this.textShapeLineHeight = textShapeLineHeight;
    }

    public float getTextShapeLineSpaceVertical() {
        return textShapeLineSpaceVertical;
    }

    public void setTextShapeLineSpaceVertical(float textShapeLineSpaceVertical) {
        this.textShapeLineSpaceVertical = textShapeLineSpaceVertical;
    }

    public static int getShapeTypeRect() {
        return SHAPE_TYPE_RECT;
    }

    public static int getShapeTypeOval() {
        return SHAPE_TYPE_OVAL;
    }

    public static int getShapeTypeText() {
        return SHAPE_TYPE_TEXT;
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

    public static int getAnimationTypeNon() {
        return ANIMATION_TYPE_NON;
    }

    public static int getAnimationTypeAlpha() {
        return ANIMATION_TYPE_ALPHA;
    }

    public static int getAnimationTypeGradient() {
        return ANIMATION_TYPE_GRADIENT;
    }

    public static int getTextShapeLineLastWidthFull() {
        return TEXT_SHAPE_LINE_LAST_WIDTH_FULL;
    }

    public static int getTextShapeLineLastWidthThreeQuarters() {
        return TEXT_SHAPE_LINE_LAST_WIDTH_THREE_QUARTERS;
    }

    public static int getTextShapeLineLastWidthHalf() {
        return TEXT_SHAPE_LINE_LAST_WIDTH_HALF;
    }

    public static int getTextShapeLineLastWidthQuarter() {
        return TEXT_SHAPE_LINE_LAST_WIDTH_QUARTER;
    }

    public static int getDefaultColorBackgroundMain() {
        return DEFAULT_COLOR_BACKGROUND_MAIN;
    }

    public static int getDefaultColorBackgroundViews() {
        return DEFAULT_COLOR_BACKGROUND_VIEWS;
    }

    public static int getDefaultColorHighlightGradient() {
        return DEFAULT_COLOR_HIGHLIGHT_GRADIENT;
    }

    public static int getDefaultAnimationDuration() {
        return DEFAULT_ANIMATION_DURATION;
    }

    public static int getDefaultAnimationDirection() {
        return DEFAULT_ANIMATION_DIRECTION;
    }

    public static int getDefaultAnimationType() {
        return DEFAULT_ANIMATION_TYPE;
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

    public static int getDefaultTextShapeLine() {
        return DEFAULT_TEXT_SHAPE_LINE;
    }

    public static int getDefaultTextShapeLastLineWidth() {
        return DEFAULT_TEXT_SHAPE_LAST_LINE_WIDTH;
    }

    public static int getDefaultTextShapeLineHeight() {
        return DEFAULT_TEXT_SHAPE_LINE_HEIGHT;
    }

    public static int getDefaultTextShapeLineSpaceVertical() {
        return DEFAULT_TEXT_SHAPE_LINE_SPACE_VERTICAL;
    }

    @Override
    public String toString() {
        return "SkeletonAttribute{" +
                "X1=" + X1 +
                ", Y1=" + Y1 +
                ", X2=" + X2 +
                ", Y2=" + Y2 +
                ", isShowSkeleton=" + isShowSkeleton +
                ", isAutoStartAnimation=" + isAutoStartAnimation +
                ", isHoldTouchEventsFromChildren=" + isHoldTouchEventsFromChildren +
                ", shapeType=" + shapeType +
                ", colorBackgroundMain=" + colorBackgroundMain +
                ", colorBackgroundViews=" + colorBackgroundViews +
                ", colorHighLight=" + colorHighLight +
                ", animationDuration=" + animationDuration +
                ", animationDirection=" + animationDirection +
                ", animationNormalType=" + animationNormalType +
                ", animationFinishType=" + animationFinishType +
                ", cornerRadius=" + cornerRadius +
                ", cornerRadiusTopLeft=" + cornerRadiusTopLeft +
                ", cornerRadiusTopRight=" + cornerRadiusTopRight +
                ", cornerRadiusBottomLeft=" + cornerRadiusBottomLeft +
                ", cornerRadiusBottomLRight=" + cornerRadiusBottomLRight +
                ", padding=" + padding +
                ", paddingTop=" + paddingTop +
                ", paddingRight=" + paddingRight +
                ", paddingBottom=" + paddingBottom +
                ", paddingLeft=" + paddingLeft +
                ", textShapeLineNumber=" + textShapeLineNumber +
                ", textShapeLineLastWidth=" + textShapeLineLastWidth +
                ", textShapeLineHeight=" + textShapeLineHeight +
                ", textShapeLineSpaceVertical=" + textShapeLineSpaceVertical +
                '}';
    }
}