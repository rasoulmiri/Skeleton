package io.rmiri.skeleton.master;

import android.graphics.RectF;
import android.view.View;

import java.util.ArrayList;

import static io.rmiri.skeleton.master.SkeletonModel.ANIMATION_DIRECTION_LTR;
import static io.rmiri.skeleton.master.SkeletonModel.ANIMATION_TYPE_GRADIENT;
import static io.rmiri.skeleton.master.SkeletonModel.DEFAULT_ANIMATION_DURATION;
import static io.rmiri.skeleton.master.SkeletonModel.DEFAULT_COLOR_BACKGROUND_MAIN;
import static io.rmiri.skeleton.master.SkeletonModel.DEFAULT_COLOR_BACKGROUND_VIEWS;
import static io.rmiri.skeleton.master.SkeletonModel.DEFAULT_COLOR_HIGHLIGHT_GRADIENT;
import static io.rmiri.skeleton.master.SkeletonModel.DEFAULT_TEXT_SHAPE_LAST_LINE_WIDTH;
import static io.rmiri.skeleton.master.SkeletonModel.DEFAULT_TEXT_SHAPE_LINE;
import static io.rmiri.skeleton.master.SkeletonModel.DEFAULT_TEXT_SHAPE_LINE_HEIGHT;
import static io.rmiri.skeleton.master.SkeletonModel.DEFAULT_TEXT_SHAPE_LINE_SPACE_VERTICAL;
import static io.rmiri.skeleton.master.SkeletonModel.SHAPE_TYPE_RECT;


public class SkeletonModelBuilder {
    private View childView = null;
    private View startView = null;
    private View endView = null;
    private float customWidth = Float.MIN_VALUE;
    private float customHeight = Float.MIN_VALUE;
    private boolean isMatchViewBoolean = false;
    private RectF rectF = null;
    private ArrayList<Float> cornerRadiusFloatArray = null;
    private float x1 = 0f;
    private float y1 = 0f;
    private float x2 = 0f;
    private float y2 = 0f;
    private boolean isShowSkeleton = true;
    private boolean isAutoStartAnimation = true;
    private boolean isHoldTouchEventsFromChildren = true;
    private int shapeType = SHAPE_TYPE_RECT;
    private int colorBackgroundMain = DEFAULT_COLOR_BACKGROUND_MAIN;
    private int colorBackgroundViews = DEFAULT_COLOR_BACKGROUND_VIEWS;
    private int colorHighLight = DEFAULT_COLOR_HIGHLIGHT_GRADIENT;
    private long animationDuration = DEFAULT_ANIMATION_DURATION;
    private int animationDirection = ANIMATION_DIRECTION_LTR;
    private int animationNormalType = ANIMATION_TYPE_GRADIENT;
    private int animationFinishType = ANIMATION_TYPE_GRADIENT;
    private int cornerRadius = Integer.MIN_VALUE;
    private int cornerRadiusTopLeft = Integer.MIN_VALUE;
    private int cornerRadiusTopRight = Integer.MIN_VALUE;
    private int cornerRadiusBottomLeft = Integer.MIN_VALUE;
    private int cornerRadiusBottomLRight = Integer.MIN_VALUE;
    private float padding = Float.MIN_VALUE;
    private float paddingTop = Float.MIN_VALUE;
    private float paddingRight = Float.MIN_VALUE;
    private float paddingBottom = Float.MIN_VALUE;
    private float paddingLeft = Float.MIN_VALUE;
    private int textShapeLineNumber = DEFAULT_TEXT_SHAPE_LINE;
    private int textShapeLineLastWidth = DEFAULT_TEXT_SHAPE_LAST_LINE_WIDTH;
    private int textShapeLineHeight = DEFAULT_TEXT_SHAPE_LINE_HEIGHT;
    private int textShapeLineSpaceVertical = DEFAULT_TEXT_SHAPE_LINE_SPACE_VERTICAL;

    public SkeletonModelBuilder setChildView(View childView) {
        this.childView = childView;
        return this;
    }

    public SkeletonModelBuilder setStartView(View startView) {
        this.startView = startView;
        return this;
    }

    public SkeletonModelBuilder setEndView(View endView) {
        this.endView = endView;
        return this;
    }

    public SkeletonModelBuilder setCustomWidth(float customWidth) {
        this.customWidth = customWidth;
        return this;
    }

    public SkeletonModelBuilder setCustomHeight(float customHeight) {
        this.customHeight = customHeight;
        return this;
    }

    public SkeletonModelBuilder setIsMatchViewBoolean(boolean isMatchViewBoolean) {
        this.isMatchViewBoolean = isMatchViewBoolean;
        return this;
    }

    public SkeletonModelBuilder setRectF(RectF rectF) {
        this.rectF = rectF;
        return this;
    }

    public SkeletonModelBuilder setCornerRadiusFloatArray(ArrayList<Float> cornerRadiusFloatArray) {
        this.cornerRadiusFloatArray = cornerRadiusFloatArray;
        return this;
    }

    public SkeletonModelBuilder setX1(float x1) {
        this.x1 = x1;
        return this;
    }

    public SkeletonModelBuilder setY1(float y1) {
        this.y1 = y1;
        return this;
    }

    public SkeletonModelBuilder setX2(float x2) {
        this.x2 = x2;
        return this;
    }

    public SkeletonModelBuilder setY2(float y2) {
        this.y2 = y2;
        return this;
    }

    public SkeletonModelBuilder setIsShowSkeleton(boolean isShowSkeleton) {
        this.isShowSkeleton = isShowSkeleton;
        return this;
    }

    public SkeletonModelBuilder setIsAutoStartAnimation(boolean isAutoStartAnimation) {
        this.isAutoStartAnimation = isAutoStartAnimation;
        return this;
    }

    public SkeletonModelBuilder setIsHoldTouchEventsFromChildren(boolean isHoldTouchEventsFromChildren) {
        this.isHoldTouchEventsFromChildren = isHoldTouchEventsFromChildren;
        return this;
    }

    public SkeletonModelBuilder setShapeType(int shapeType) {
        this.shapeType = shapeType;
        return this;
    }

    public SkeletonModelBuilder setColorBackgroundMain(int colorBackgroundMain) {
        this.colorBackgroundMain = colorBackgroundMain;
        return this;
    }

    public SkeletonModelBuilder setColorBackgroundViews(int colorBackgroundViews) {
        this.colorBackgroundViews = colorBackgroundViews;
        return this;
    }

    public SkeletonModelBuilder setColorHighLight(int colorHighLight) {
        this.colorHighLight = colorHighLight;
        return this;
    }

    public SkeletonModelBuilder setAnimationDuration(long animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public SkeletonModelBuilder setAnimationDirection(int animationDirection) {
        this.animationDirection = animationDirection;
        return this;
    }

    public SkeletonModelBuilder setAnimationNormalType(int animationNormalType) {
        this.animationNormalType = animationNormalType;
        return this;
    }

    public SkeletonModelBuilder setAnimationFinishType(int animationFinishType) {
        this.animationFinishType = animationFinishType;
        return this;
    }

    public SkeletonModelBuilder setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        return this;
    }

    public SkeletonModelBuilder setCornerRadiusTopLeft(int cornerRadiusTopLeft) {
        this.cornerRadiusTopLeft = cornerRadiusTopLeft;
        return this;
    }

    public SkeletonModelBuilder setCornerRadiusTopRight(int cornerRadiusTopRight) {
        this.cornerRadiusTopRight = cornerRadiusTopRight;
        return this;
    }

    public SkeletonModelBuilder setCornerRadiusBottomLeft(int cornerRadiusBottomLeft) {
        this.cornerRadiusBottomLeft = cornerRadiusBottomLeft;
        return this;
    }

    public SkeletonModelBuilder setCornerRadiusBottomLRight(int cornerRadiusBottomLRight) {
        this.cornerRadiusBottomLRight = cornerRadiusBottomLRight;
        return this;
    }

    public SkeletonModelBuilder setPadding(float padding) {
        this.padding = padding;
        return this;
    }

    public SkeletonModelBuilder setPaddingTop(float paddingTop) {
        this.paddingTop = paddingTop;
        return this;
    }

    public SkeletonModelBuilder setPaddingRight(float paddingRight) {
        this.paddingRight = paddingRight;
        return this;
    }

    public SkeletonModelBuilder setPaddingBottom(float paddingBottom) {
        this.paddingBottom = paddingBottom;
        return this;
    }

    public SkeletonModelBuilder setPaddingLeft(float paddingLeft) {
        this.paddingLeft = paddingLeft;
        return this;
    }

    public SkeletonModelBuilder setTextShapeLineNumber(int textShapeLineNumber) {
        this.textShapeLineNumber = textShapeLineNumber;
        return this;
    }

    public SkeletonModelBuilder setTextShapeLineLastWidth(int textShapeLineLastWidth) {
        this.textShapeLineLastWidth = textShapeLineLastWidth;
        return this;
    }

    public SkeletonModelBuilder setTextShapeLineHeight(int textShapeLineHeight) {
        this.textShapeLineHeight = textShapeLineHeight;
        return this;
    }

    public SkeletonModelBuilder setTextShapeLineSpaceVertical(int textShapeLineSpaceVertical) {
        this.textShapeLineSpaceVertical = textShapeLineSpaceVertical;
        return this;
    }

    public SkeletonModel build() {
        return new SkeletonModel(childView, startView, endView, customWidth, customHeight, isMatchViewBoolean, rectF, cornerRadiusFloatArray, x1, y1, x2, y2, isShowSkeleton, isAutoStartAnimation, isHoldTouchEventsFromChildren, shapeType, colorBackgroundMain, colorBackgroundViews, colorHighLight, animationDuration, animationDirection, animationNormalType, animationFinishType, cornerRadius, cornerRadiusTopLeft, cornerRadiusTopRight, cornerRadiusBottomLeft, cornerRadiusBottomLRight, padding, paddingTop, paddingRight, paddingBottom, paddingLeft, textShapeLineNumber, textShapeLineLastWidth, textShapeLineHeight, textShapeLineSpaceVertical);
    }
}