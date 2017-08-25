package io.rmiri.placeholder;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import io.rmiri.placeholder.utils.CLog;
import io.rmiri.placeholder.Master.PlaceholderAttribute;
import io.rmiri.placeholder.Master.PlaceholderMaster;
import io.rmiri.placeholder.utils.ColorUtils;


/**
 * Created by Rasoul Miri on 8/8/17.
 */

public class PlaceholderGroup extends PlaceholderMaster {

    private ArrayList<PlaceholderAttribute> placeholderAttributesChildren;// array for all children extend from PlaceholderGradient
    private PlaceholderListener placeholderListener; // listener

    /* variable for draw canvas */
    private Paint paintGradient;
    private Paint paintBackgroundViews;
    private Canvas canvasGradientElement;
    private Canvas canvasBackgroundElement;
    private Bitmap bitmapGradientElement;
    private Bitmap bitmapBackgroundElement;
    private Path path = new Path();

    /* variable for gradient */
    private int[] gradientColors; // is 3 colors
    private float xStartGradient, yStartGradient, xEndGradient, yEndGradient;

    /* variable for fraction animation played */
    private ValueAnimator valueAnimator;
    private float AnimationFractionMove = 0.0f;
    private float AnimationFraction = 0.0f;

    /* variable for checking */
    private boolean isCanDraw = false;
    private boolean isCanDrawFinishState = false;
    private boolean isAnimationPlay = false;
    private boolean isLastLoopAnimation = false;


    public PlaceholderGroup(@NonNull Context context) {
        super(context);
    }

    public PlaceholderGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PlaceholderGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlaceholderGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void init(Context context, @Nullable AttributeSet attrs) {
        super.init(context, attrs);

        //generate color 010 (color one transparent = 0.0 | color two transparent = 1.0 | color three transparent =01.0 )
        gradientColors = ColorUtils.generateColorTransparent010(placeholderAttribute.getColorHighLight(), placeholderAttribute.getColorBackgroundViews());

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        CLog.i("PlaceholderGroup onLayout " + postion);
        //add all children extend from PlaceholderGradient
        if (placeholderAttributesChildren == null && placeholderAttribute.isShowPlaceHolder()) {
            CLog.i("PlaceholderGroup onLayout and placeholderAttributesChildren == null ... " + postion);
            placeholderAttributesChildren = new ArrayList<>();
            for (View child : getAllChildren(getChildAt(0))) {
                if (child != null && child instanceof PlaceholderView) {

                    PlaceholderAttribute placeholderAttributeChild = ((PlaceholderView) child).getPlaceholderAttribute();

                    //set point X1,Y1,X2,Y2
                    float X1 = child.getLeft();
                    float Y1 = child.getTop();
                    float X2 = 0.0f;
                    float Y2 = 0.0f;

                    //calculator X1,X2 to left and top PlaceholderGroup view
                    View parent = (View) child.getParent();
                    while (parent != null && !(parent instanceof PlaceholderGroup)) {
                        X1 += parent.getLeft();
                        Y1 += parent.getTop();
                        parent = (View) parent.getParent();
                    }

                    //calculator padding size
                    float pTop;
                    float pLeft;
                    float pRight;
                    float pBottom;

                    if (placeholderAttributeChild.getPadding() != Integer.MIN_VALUE) {
                        pTop = pLeft = pRight = pBottom = placeholderAttributeChild.getPadding();
                    } else {
                        pTop = placeholderAttributeChild.getPaddingTop() != Integer.MIN_VALUE ? placeholderAttributeChild.getPaddingTop() : 0;
                        pLeft = placeholderAttributeChild.getPaddingLeft() != Integer.MIN_VALUE ? placeholderAttributeChild.getPaddingLeft() : 0;
                        pRight = placeholderAttributeChild.getPaddingRight() != Integer.MIN_VALUE ? placeholderAttributeChild.getPaddingRight() : 0;
                        pBottom = placeholderAttributeChild.getPaddingBottom() != Integer.MIN_VALUE ? placeholderAttributeChild.getPaddingBottom() : 0;
                    }

                    X2 = X1 + child.getMeasuredWidth() - pRight;
                    Y2 = Y1 + child.getMeasuredHeight() - pBottom;
                    X1 = X1 + pLeft;
                    Y1 = Y1 + pTop;

                    placeholderAttributeChild.setX1(X1);
                    placeholderAttributeChild.setY1(Y1);
                    placeholderAttributeChild.setX2(X2);
                    placeholderAttributeChild.setY2(Y2);


                    placeholderAttributesChildren.add(placeholderAttributeChild);
                }
            }
            if (placeholderAttribute.isAutoStartAnimation()) {
                setupInitialAnimation();
            }
        }

    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if (isCanDraw) {

//            CLog.i("PlaceholderGroup dispatchDraw " + postion);

            // just once run this code
            // calculate bound child for draw
            if (paintGradient == null) {

                //paint
                paintBackgroundViews = new Paint(Paint.ANTI_ALIAS_FLAG);
                paintBackgroundViews.setStyle(Paint.Style.FILL);
                paintBackgroundViews.setColor(placeholderAttribute.getColorBackgroundViews());
                paintGradient = new Paint(Paint.ANTI_ALIAS_FLAG);
                paintGradient.setStyle(Paint.Style.FILL);

                //canvas
                Bitmap.Config bitmapConfig = Bitmap.Config.ALPHA_8;
                bitmapBackgroundElement = Bitmap.createBitmap(getWidth(), getHeight(), bitmapConfig);
                canvasBackgroundElement = new Canvas(bitmapBackgroundElement);
                bitmapGradientElement = Bitmap.createBitmap(getWidth(), getHeight(), bitmapConfig);
                canvasGradientElement = new Canvas(bitmapGradientElement);

                for (int i = 0; i < placeholderAttributesChildren.size(); i++) {

                    PlaceholderAttribute placeHolderGradientChild = placeholderAttributesChildren.get(i);

                    if (placeHolderGradientChild.getShapeType() == PlaceholderAttribute.SHAPE_TYPE_RECT) {
                        // Rectangle
                        RectF rectangleRect = new RectF(placeHolderGradientChild.getX1(), placeHolderGradientChild.getY1(), placeHolderGradientChild.getX2(), placeHolderGradientChild.getY2());
                        float[] cornerRadius = generateCornerRadius(rectangleRect, placeHolderGradientChild);
                        path.addRoundRect(rectangleRect, cornerRadius, Path.Direction.CW);
                    } else if (placeHolderGradientChild.getShapeType() == PlaceholderAttribute.SHAPE_TYPE_OVAL) {
                        //Oval
                        RectF ovalRect = new RectF(placeHolderGradientChild.getX1(), placeHolderGradientChild.getY1(), placeHolderGradientChild.getX2(), placeHolderGradientChild.getY2());
                        path.addOval(ovalRect, Path.Direction.CW);
                    } else if (placeHolderGradientChild.getShapeType() == PlaceholderAttribute.SHAPE_TYPE_TEXT) {
                        //Text


                        float lineHeight = placeHolderGradientChild.getTextShapeLineHeight();
                        float lineSpaceVertical = placeHolderGradientChild.getTextShapeLineSpaceVertical();

                        int lineNumber = placeHolderGradientChild.getTextShapeLineNumber();
                        if (lineNumber == 0) {
                            lineNumber = (int) ((placeHolderGradientChild.getY2() - placeHolderGradientChild.getY1())
                                                                / (lineHeight + lineSpaceVertical));
                        }
                        CLog.i("line number  " + lineNumber);
                        for (int j = 0; j < lineNumber; j++) {

                            RectF rectangleRect;
                            float newY1, newY2;
                            float newX1 = placeHolderGradientChild.getX1();
                            float newX2 = placeHolderGradientChild.getX2();

                            //Y1
                            if (j == 0) {
                                //first line
                                newY1 = placeHolderGradientChild.getY1();
                            } else {
                                //other line
                                newY1 = placeHolderGradientChild.getY1()
                                        + (lineHeight * j) // height children previous
                                        + (lineSpaceVertical * j);// space vertical children previous
                            }

                            //Y2
                            newY2 = placeHolderGradientChild.getY1()
                                    + (lineHeight * (j + 1)) // height children previous
                                    + (lineSpaceVertical * j);// space vertical children previous


                            //X1,X2 just for last line
                            if (j == lineNumber - 1) {

                                float quarterWidth = (placeHolderGradientChild.getX2() - placeHolderGradientChild.getX1()) / 4;//quarter width 1 line

                                switch (placeHolderGradientChild.getTextShapeLineLastWidth()) {
                                    case PlaceholderAttribute.TEXT_SHAPE_LINE_LAST_WIDTH_FULL:
                                        //no change X1 and X2
                                        break;
                                    case PlaceholderAttribute.TEXT_SHAPE_LINE_LAST_WIDTH_THREE_QUARTERS:
                                        if (placeholderAttribute.getAnimationDirection() == PlaceholderAttribute.ANIMATION_DIRECTION_RTL) {
                                            newX1 = placeHolderGradientChild.getX1() + quarterWidth;
                                        } else {
                                            newX2 = placeHolderGradientChild.getX2() - quarterWidth;
                                        }
                                        break;
                                    case PlaceholderAttribute.TEXT_SHAPE_LINE_LAST_WIDTH_HALF:
                                        if (placeholderAttribute.getAnimationDirection() == PlaceholderAttribute.ANIMATION_DIRECTION_RTL) {
                                            newX1 = placeHolderGradientChild.getX1() + (2 * quarterWidth);
                                        } else {
                                            newX2 = placeHolderGradientChild.getX2() - (2 * quarterWidth);
                                        }
                                        break;
                                    case PlaceholderAttribute.TEXT_SHAPE_LINE_LAST_WIDTH_QUARTER:
                                        if (placeholderAttribute.getAnimationDirection() == PlaceholderAttribute.ANIMATION_DIRECTION_RTL) {
                                            newX1 = placeHolderGradientChild.getX1() + (3 * quarterWidth);
                                        } else {
                                            newX2 = placeHolderGradientChild.getX2() - (3 * quarterWidth);
                                        }
                                        break;
                                }
                            }

                            //draw text line
                            rectangleRect = new RectF(newX1, newY1, newX2, newY2);
                            float[] cornerRadius = generateCornerRadius(rectangleRect, placeHolderGradientChild);
                            path.addRoundRect(rectangleRect, cornerRadius, Path.Direction.CW);
                        }
                    }


                    //draw bitmap on canvas
                    canvasBackgroundElement.drawPath(path, paintBackgroundViews);
                    canvasGradientElement.drawPath(path, paintBackgroundViews);

                }

            }


            //main draw
            if (isCanDrawFinishState) {
                // finish draw
                switch (placeholderAttribute.getAnimationFinishType()) {
                    case PlaceholderAttribute.ANIMATION_TYPE_ALPHA:

                        //draw background total view with alpha
                        drawBackgroundTotal(canvas, ColorUtils.convertColorToTransparent(placeholderAttribute.getColorBackgroundMain(), 1.0f - AnimationFraction));

                        paintBackgroundViews.setAlpha((int) (255 * (1.0f - AnimationFraction)));
                        canvas.drawBitmap(bitmapBackgroundElement, 0, 0, paintBackgroundViews);

                        break;
                    case PlaceholderAttribute.ANIMATION_TYPE_GRADIENT:

                        //draw background total view with alpha
                        drawBackgroundTotal(canvas, ColorUtils.convertColorToTransparent(placeholderAttribute.getColorBackgroundMain(), 1.0f - AnimationFraction));

                        generatePositionGradientFromDirectionAnimation();
                        paintGradient.setShader(new LinearGradient(xStartGradient, yStartGradient, xEndGradient, yEndGradient
                                , gradientColors
                                , new float[]{0f, 0.4f, 0.8f}
                                , Shader.TileMode.CLAMP));

                        canvas.drawBitmap(bitmapGradientElement, 0, 0, paintGradient);
                        break;
                }

            } else {
                // normal draw

                drawBackgroundTotal(canvas, placeholderAttribute.getColorBackgroundMain());//draw background total
                canvas.drawBitmap(bitmapBackgroundElement, 0, 0, paintBackgroundViews);//draw background view
                switch (placeholderAttribute.getAnimationNormalType()) {

                    case PlaceholderAttribute.ANIMATION_TYPE_ALPHA:

                        float alpha = 0.0f;
//                        if (AnimationFractionMove >= 0) {
//                            alpha = AnimationFractionMove;
//                        } else {
//                            alpha = AnimationFractionMove + 1;
//                        }
                        CLog.i(alpha + "");
                        paintGradient.setColor(ColorUtils.convertColorToTransparent(placeholderAttribute.getColorHighLight(), Math.abs(AnimationFractionMove)));

                        canvas.drawBitmap(bitmapGradientElement, 0, 0, paintGradient);

                        break;
                    case PlaceholderAttribute.ANIMATION_TYPE_GRADIENT:

                        generatePositionGradientFromDirectionAnimation();
                        paintGradient.setShader(new LinearGradient(xStartGradient, yStartGradient, xEndGradient, yEndGradient
                                , gradientColors
                                , new float[]{0f, 0.4f, 0.8f}
                                , Shader.TileMode.CLAMP));

                        canvas.drawBitmap(bitmapGradientElement, 0, 0, paintGradient);

                        break;
                }


            }


        }

    }


    private float[] generateCornerRadius(RectF rectangleRect, PlaceholderAttribute placeHolderGradientChild) {

        //calculator cornerRadius size
        float cornerRadiusTopLeft;
        float cornerRadiusTopRight;
        float cornerRadiusBottomLRight;
        float cornerRadiusBottomLeft;

        if (placeHolderGradientChild.getCornerRadius() != Integer.MIN_VALUE) {
            cornerRadiusTopLeft = cornerRadiusTopRight = cornerRadiusBottomLRight = cornerRadiusBottomLeft = getCornerRadius(rectangleRect, placeHolderGradientChild.getCornerRadius());
        } else {
            cornerRadiusTopLeft = placeHolderGradientChild.getCornerRadiusTopLeft() != Integer.MIN_VALUE ? getCornerRadius(rectangleRect, placeHolderGradientChild.getCornerRadiusTopLeft()) : 0;
            cornerRadiusTopRight = placeHolderGradientChild.getCornerRadiusTopRight() != Integer.MIN_VALUE ? getCornerRadius(rectangleRect, placeHolderGradientChild.getCornerRadiusTopRight()) : 0;
            cornerRadiusBottomLRight = placeHolderGradientChild.getCornerRadiusBottomLRight() != Integer.MIN_VALUE ? getCornerRadius(rectangleRect, placeHolderGradientChild.getCornerRadiusBottomLRight()) : 0;
            cornerRadiusBottomLeft = placeHolderGradientChild.getCornerRadiusBottomLeft() != Integer.MIN_VALUE ? getCornerRadius(rectangleRect, placeHolderGradientChild.getCornerRadiusBottomLeft()) : 0;
        }

        return new float[]{cornerRadiusTopLeft, cornerRadiusTopLeft, cornerRadiusTopRight, cornerRadiusTopRight, cornerRadiusBottomLRight, cornerRadiusBottomLRight, cornerRadiusBottomLeft, cornerRadiusBottomLeft};

    }

    private float getCornerRadius(RectF rectangleRect, float initCornerRadius) {
        return Math.min(Math.min(rectangleRect.width(), rectangleRect.height()) / 2f, initCornerRadius);
    }

    private void drawBackgroundTotal(Canvas canvas, int color) {
        //for transparent no need draw color
        if (color != android.R.color.transparent) {
            canvas.drawColor(color);//background total view
        }
    }

    private void generatePositionGradientFromDirectionAnimation() {
        //check direction and set x,y start and end for gradient
        switch (placeholderAttribute.getAnimationDirection()) {
            case PlaceholderAttribute.ANIMATION_DIRECTION_LTR: {
                float offset = getWidth() * AnimationFractionMove;
                xStartGradient = offset;
                yStartGradient = 0;
                xEndGradient = offset + getWidth();
                yEndGradient = 0;
                break;
            }
            case PlaceholderAttribute.ANIMATION_DIRECTION_RTL: {
                float offset = getWidth() * AnimationFractionMove;
                xStartGradient = offset;
                yStartGradient = 0;
                xEndGradient = offset + getWidth();
                yEndGradient = 0;
                break;
            }
            case PlaceholderAttribute.ANIMATION_DIRECTION_TTB: {
                float offset = getHeight() * AnimationFractionMove;
                xStartGradient = 0;
                yStartGradient = offset;
                xEndGradient = 0;
                yEndGradient = offset + getHeight();
                break;
            }
            case PlaceholderAttribute.ANIMATION_DIRECTION_BTT: {
                float offset = getHeight() * AnimationFractionMove;
                xStartGradient = 0;
                yStartGradient = offset;
                xEndGradient = 0;
                yEndGradient = offset + getHeight();
                break;
            }
        }
    }


    private void setupInitialAnimation() {

        if (placeholderAttributesChildren != null && placeholderAttributesChildren.size() > 0) {
            isCanDraw = true;
            isAnimationPlay = true;
        } else {
            // placeholderGradientGroup no child extend from placeholderGradient
            return;
        }

        //checking direction animation for generate value star and end move animation fraction
        float valueAnimationMoveFractionStart = 0;
        float valueAnimationMoveFractionEnd = 0;
        switch (placeholderAttribute.getAnimationDirection()) {
            case PlaceholderAttribute.ANIMATION_DIRECTION_LTR:
                valueAnimationMoveFractionStart = -1;
                valueAnimationMoveFractionEnd = 1;
                break;
            case PlaceholderAttribute.ANIMATION_DIRECTION_RTL:
                valueAnimationMoveFractionStart = 1;
                valueAnimationMoveFractionEnd = -1;
                break;
            case PlaceholderAttribute.ANIMATION_DIRECTION_TTB:
                valueAnimationMoveFractionStart = -1;
                valueAnimationMoveFractionEnd = 1;
                break;
            case PlaceholderAttribute.ANIMATION_DIRECTION_BTT:
                valueAnimationMoveFractionStart = 1;
                valueAnimationMoveFractionEnd = -1;
                break;
        }

        //initial animation
        valueAnimator = ValueAnimator.ofFloat(valueAnimationMoveFractionStart, valueAnimationMoveFractionEnd);
        valueAnimator.setDuration(placeholderAttribute.getAnimationDuration());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                CLog.i("PlaceholderGroup onAnimationRepeat " + postion);

                if (isCanDrawFinishState) {
                    CLog.i("PlaceholderGroup isCanDrawFinishState " + postion);
                    setupFinishingAnimation();
                }


                if (isLastLoopAnimation) {
                    // last repeat animation and start finish animation
                    CLog.i("PlaceholderGroup isLastLoopAnimatio " + postion);
                    if (placeholderAttribute.getAnimationFinishType() == PlaceholderAttribute.ANIMATION_TYPE_NON) {
                        setupFinishingAnimation();
                    } else {
                        isLastLoopAnimation = false;
                        isCanDrawFinishState = true;
                        //change color 011 (color one transparent = 0.0 | color two transparent = 1.0 | color three transparent = 1.0 )
                        gradientColors = ColorUtils.generateColorTransparent011(placeholderAttribute.getColorHighLight(), placeholderAttribute.getColorBackgroundViews());
                    }
                }

            }
        });

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                AnimationFractionMove = value;
                AnimationFraction = animation.getAnimatedFraction();
                invalidate();
            }
        });

        valueAnimator.start();

        //fire for listener
        if (placeholderListener != null)
            placeholderListener.onStartAnimation();

    }


    private void setupFinishingAnimation() {

        isCanDraw = false;
        isLastLoopAnimation = false;
        isCanDrawFinishState = false;

        //remove animation listener
        valueAnimator.removeAllListeners();
        valueAnimator.end();
        valueAnimator.cancel();


        // disable Hold touchEvents from this and children
        setHoldTouchEventsFromChildren(false);
        for (View child : getAllChildren(getChildAt(0))) {
            if (child != null && child instanceof PlaceholderView) {
                ((PlaceholderView) child).setHoldTouchEventsFromChildren(false);
            }
        }

        //fire finish state for listener
        if (placeholderListener != null)
            placeholderListener.onFinishAnimation();

    }


    public void startAnimation() {
        if (!isAnimationPlay) {
            setupInitialAnimation();
        }
    }

    public void finishAnimation() {
        if (isAnimationPlay) {
            isLastLoopAnimation = true;
        }
    }

    public void setAutoPlay(boolean isAnimationAutoStart) {
        placeholderAttribute.setAutoStartAnimation(isAnimationAutoStart);
    }

    public void setShowPlaceHolder(boolean isShowPlaceHolder) {
        placeholderAttribute.setShowPlaceHolder(isShowPlaceHolder);
    }

    //==============================================================================================
    /*Listener*/
    public interface PlaceholderListener {
        void onStartAnimation();

        void onFinishAnimation();

    }

    public void setPlaceholderListener(PlaceholderListener placeholderListener) {
        this.placeholderListener = placeholderListener;
    }
    //==============================================================================================
}