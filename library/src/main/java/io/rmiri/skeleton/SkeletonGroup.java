package io.rmiri.skeleton;

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
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import io.rmiri.skeleton.Master.SkeletonAttribute;
import io.rmiri.skeleton.Master.SkeletonMaster;
import io.rmiri.skeleton.utils.CLog;
import io.rmiri.skeleton.utils.ColorUtils;


/**
 * Created by Rasoul Miri on 8/8/17.
 */

public class SkeletonGroup extends SkeletonMaster {

    private ArrayList<SkeletonAttribute> skeletonAttributesChildren;// Array for all children extend from SkeletonView
    private SkeletonListener skeletonListener; // Listener

    /* Variable for draw canvas */
    private Paint paintGradient;
    private Paint paintBackgroundViews;
    private Canvas canvasGradientElement;
    private Canvas canvasBackgroundElement;
    private Bitmap bitmapGradientElement;
    private Bitmap bitmapBackgroundElement;
    private Path path = new Path();

    /* Variable for gradient */
    private int[] gradientColors; // is 3 colors
    private float xStartGradient, yStartGradient, xEndGradient, yEndGradient;

    /* Variable for fraction animation played */
    private ValueAnimator valueAnimator;
    private float AnimationFractionMove = 0.0f;
    private float AnimationFraction = 0.0f;

    /* Variable for checking */
    private boolean isCanDraw = false;
    private boolean isCanDrawFinishState = false;
    private boolean isAnimationPlay = false;
    private boolean isLastLoopAnimation = false;


    public SkeletonGroup(@NonNull Context context) {
        super(context);
    }

    public SkeletonGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SkeletonGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SkeletonGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void init(Context context, @Nullable AttributeSet attrs) {
        super.init(context, attrs);

        // Generate color 010 (color one transparent = 0.0 | color two transparent = 1.0 | color three transparent =01.0 )
        gradientColors = ColorUtils.generateColorTransparent010(skeletonAttribute.getColorHighLight(), skeletonAttribute.getColorBackgroundViews());

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        CLog.i("SkeletonGroup onLayout " + position);
        // Add all children extend from SkeletonView
        if (skeletonAttributesChildren == null && skeletonAttribute.isShowSkeleton()) {
            CLog.i("SkeletonGroup onLayout and skeletonAttributesChildren == null ... " + position);
            skeletonAttributesChildren = new ArrayList<>();
            for (View child : getAllChildren(getChildAt(0))) {
                if (child != null && child instanceof SkeletonView) {

                    SkeletonAttribute skeletonAttributeChild = ((SkeletonView) child).getSkeletonAttribute();

                    // Set point X1,Y1,X2,Y2
                    float X1 = child.getLeft();
                    float Y1 = child.getTop();
                    float X2 = 0.0f;
                    float Y2 = 0.0f;

                    // Calculator X1,X2 to left and top SkeletonGroup view
                    View parent = (View) child.getParent();
                    while (parent != null && !(parent instanceof SkeletonGroup)) {
                        X1 += parent.getLeft();
                        Y1 += parent.getTop();
                        parent = (View) parent.getParent();
                    }

                    // Calculator padding size
                    float pTop;
                    float pLeft;
                    float pRight;
                    float pBottom;

                    if (skeletonAttributeChild.getPadding() != Integer.MIN_VALUE) {
                        pTop = pLeft = pRight = pBottom = skeletonAttributeChild.getPadding();
                    } else {
                        pTop = skeletonAttributeChild.getPaddingTop() != Integer.MIN_VALUE ? skeletonAttributeChild.getPaddingTop() : 0;
                        pLeft = skeletonAttributeChild.getPaddingLeft() != Integer.MIN_VALUE ? skeletonAttributeChild.getPaddingLeft() : 0;
                        pRight = skeletonAttributeChild.getPaddingRight() != Integer.MIN_VALUE ? skeletonAttributeChild.getPaddingRight() : 0;
                        pBottom = skeletonAttributeChild.getPaddingBottom() != Integer.MIN_VALUE ? skeletonAttributeChild.getPaddingBottom() : 0;
                    }

                    X2 = X1 + child.getMeasuredWidth() - pRight;
                    Y2 = Y1 + child.getMeasuredHeight() - pBottom;
                    X1 = X1 + pLeft;
                    Y1 = Y1 + pTop;

                    skeletonAttributeChild.setX1(X1);
                    skeletonAttributeChild.setY1(Y1);
                    skeletonAttributeChild.setX2(X2);
                    skeletonAttributeChild.setY2(Y2);


                    skeletonAttributesChildren.add(skeletonAttributeChild);
                }
            }
            if (skeletonAttribute.isAutoStartAnimation()) {
                setupInitialAnimation();
            }
        }

    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if (isCanDraw) {

//            CLog.i("SkeletonGroup dispatchDraw " + position);

            // Just once run this code
            // Calculate bound child for draw
            if (paintGradient == null) {

                //paint
                paintBackgroundViews = new Paint(Paint.ANTI_ALIAS_FLAG);
                paintBackgroundViews.setStyle(Paint.Style.FILL);
                paintBackgroundViews.setColor(skeletonAttribute.getColorBackgroundViews());
                paintGradient = new Paint(Paint.ANTI_ALIAS_FLAG);
                paintGradient.setStyle(Paint.Style.FILL);

                //canvas
                Bitmap.Config bitmapConfig = Bitmap.Config.ALPHA_8;
                bitmapBackgroundElement = Bitmap.createBitmap(getWidth(), getHeight(), bitmapConfig);
                canvasBackgroundElement = new Canvas(bitmapBackgroundElement);
                bitmapGradientElement = Bitmap.createBitmap(getWidth(), getHeight(), bitmapConfig);
                canvasGradientElement = new Canvas(bitmapGradientElement);

                for (int i = 0; i < skeletonAttributesChildren.size(); i++) {

                    SkeletonAttribute skeletonAttributeChild = skeletonAttributesChildren.get(i);

                    if (skeletonAttributeChild.getShapeType() == SkeletonAttribute.SHAPE_TYPE_RECT) {
                        // Rectangle
                        RectF rectangleRect = new RectF(skeletonAttributeChild.getX1(), skeletonAttributeChild.getY1(), skeletonAttributeChild.getX2(), skeletonAttributeChild.getY2());
                        float[] cornerRadius = generateCornerRadius(rectangleRect, skeletonAttributeChild);
                        path.addRoundRect(rectangleRect, cornerRadius, Path.Direction.CW);
                    } else if (skeletonAttributeChild.getShapeType() == SkeletonAttribute.SHAPE_TYPE_OVAL) {
                        // Oval
                        RectF ovalRect = new RectF(skeletonAttributeChild.getX1(), skeletonAttributeChild.getY1(), skeletonAttributeChild.getX2(), skeletonAttributeChild.getY2());
                        path.addOval(ovalRect, Path.Direction.CW);
                    } else if (skeletonAttributeChild.getShapeType() == SkeletonAttribute.SHAPE_TYPE_TEXT) {
                        // Text


                        float lineHeight = skeletonAttributeChild.getTextShapeLineHeight();
                        float lineSpaceVertical = skeletonAttributeChild.getTextShapeLineSpaceVertical();

                        int lineNumber = skeletonAttributeChild.getTextShapeLineNumber();
                        if (lineNumber == 0) {
                            lineNumber = (int) ((skeletonAttributeChild.getY2() - skeletonAttributeChild.getY1())
                                    / (lineHeight + lineSpaceVertical));
                        }
                        CLog.i("line number  " + lineNumber);
                        for (int j = 0; j < lineNumber; j++) {

                            RectF rectangleRect;
                            float newY1, newY2;
                            float newX1 = skeletonAttributeChild.getX1();
                            float newX2 = skeletonAttributeChild.getX2();

                            // Y1
                            if (j == 0) {
                                // First line
                                newY1 = skeletonAttributeChild.getY1();
                            } else {
                                // Other line
                                newY1 = skeletonAttributeChild.getY1()
                                        + (lineHeight * j) // height children previous
                                        + (lineSpaceVertical * j);// space vertical children previous
                            }

                            // Y2
                            newY2 = skeletonAttributeChild.getY1()
                                    + (lineHeight * (j + 1)) // height children previous
                                    + (lineSpaceVertical * j);// space vertical children previous


                            // X1,X2 just for last line
                            if (j == lineNumber - 1) {

                                float quarterWidth = (skeletonAttributeChild.getX2() - skeletonAttributeChild.getX1()) / 4;//quarter width 1 line

                                switch (skeletonAttributeChild.getTextShapeLineLastWidth()) {
                                    case SkeletonAttribute.TEXT_SHAPE_LINE_LAST_WIDTH_FULL:
                                        // No change X1 and X2
                                        break;
                                    case SkeletonAttribute.TEXT_SHAPE_LINE_LAST_WIDTH_THREE_QUARTERS:
                                        if (skeletonAttribute.getAnimationDirection() == SkeletonAttribute.ANIMATION_DIRECTION_RTL) {
                                            newX1 = skeletonAttributeChild.getX1() + quarterWidth;
                                        } else {
                                            newX2 = skeletonAttributeChild.getX2() - quarterWidth;
                                        }
                                        break;
                                    case SkeletonAttribute.TEXT_SHAPE_LINE_LAST_WIDTH_HALF:
                                        if (skeletonAttribute.getAnimationDirection() == SkeletonAttribute.ANIMATION_DIRECTION_RTL) {
                                            newX1 = skeletonAttributeChild.getX1() + (2 * quarterWidth);
                                        } else {
                                            newX2 = skeletonAttributeChild.getX2() - (2 * quarterWidth);
                                        }
                                        break;
                                    case SkeletonAttribute.TEXT_SHAPE_LINE_LAST_WIDTH_QUARTER:
                                        if (skeletonAttribute.getAnimationDirection() == SkeletonAttribute.ANIMATION_DIRECTION_RTL) {
                                            newX1 = skeletonAttributeChild.getX1() + (3 * quarterWidth);
                                        } else {
                                            newX2 = skeletonAttributeChild.getX2() - (3 * quarterWidth);
                                        }
                                        break;
                                }
                            }

                            // Draw text line
                            rectangleRect = new RectF(newX1, newY1, newX2, newY2);
                            float[] cornerRadius = generateCornerRadius(rectangleRect, skeletonAttributeChild);
                            path.addRoundRect(rectangleRect, cornerRadius, Path.Direction.CW);
                        }
                    }


                    // Draw bitmap on canvas
                    canvasBackgroundElement.drawPath(path, paintBackgroundViews);
                    canvasGradientElement.drawPath(path, paintBackgroundViews);

                }

            }


            // Main draw
            if (isCanDrawFinishState) {
                // Finish draw
                switch (skeletonAttribute.getAnimationFinishType()) {
                    case SkeletonAttribute.ANIMATION_TYPE_ALPHA:

                        // Draw background total view with alpha
                        drawBackgroundTotal(canvas, ColorUtils.convertColorToTransparent(skeletonAttribute.getColorBackgroundMain(), 1.0f - AnimationFraction));

                        paintBackgroundViews.setAlpha((int) (255 * (1.0f - AnimationFraction)));
                        canvas.drawBitmap(bitmapBackgroundElement, 0, 0, paintBackgroundViews);

                        break;
                    case SkeletonAttribute.ANIMATION_TYPE_GRADIENT:

                        // Draw background total view with alpha
                        // drawBackgroundTotal(canvas, ColorUtils.convertColorToTransparent(skeletonAttribute.getColorBackgroundMain(), 1.0f - AnimationFraction));


//                        Log.i("++++++++++++", "+++++++++++++++++++++++++++++++++++++++++)))))))))))))" + AnimationFraction + "                                " + AnimationFractionMove);
                        if (AnimationFractionMove == 1f || AnimationFractionMove == -1f) {
                            drawBackgroundTotal(canvas, skeletonAttribute.getColorBackgroundMain());//draw background total
                            canvas.drawBitmap(bitmapBackgroundElement, 0, 0, paintBackgroundViews);//draw background view
                        }

                        generatePositionGradientFromDirectionAnimation();
                        paintGradient.setShader(new LinearGradient(xStartGradient, yStartGradient, xEndGradient, yEndGradient
                                , gradientColors
                                , new float[]{0f, 0.4f, 0.8f}
                                , Shader.TileMode.CLAMP));

                        canvas.drawBitmap(bitmapGradientElement, 0, 0, paintGradient);
                        break;
                }

            } else {
                // Normal draw

                drawBackgroundTotal(canvas, skeletonAttribute.getColorBackgroundMain());//draw background total
                canvas.drawBitmap(bitmapBackgroundElement, 0, 0, paintBackgroundViews);//draw background view
                switch (skeletonAttribute.getAnimationNormalType()) {

                    case SkeletonAttribute.ANIMATION_TYPE_ALPHA:

                        float alpha = 0.0f;
//                        if (AnimationFractionMove >= 0) {
//                            alpha = AnimationFractionMove;
//                        } else {
//                            alpha = AnimationFractionMove + 1;
//                        }
                        CLog.i(alpha + "");
                        paintGradient.setColor(ColorUtils.convertColorToTransparent(skeletonAttribute.getColorHighLight(), Math.abs(AnimationFractionMove)));

                        canvas.drawBitmap(bitmapGradientElement, 0, 0, paintGradient);

                        break;
                    case SkeletonAttribute.ANIMATION_TYPE_GRADIENT:

                        generatePositionGradientFromDirectionAnimation();
                        paintGradient.setShader(new LinearGradient(xStartGradient, yStartGradient, xEndGradient, yEndGradient
                                , gradientColors
                                , new float[]{0f, 0.4f, 0.8f}
                                , Shader.TileMode.CLAMP));

                        canvas.drawBitmap(bitmapGradientElement, 0, 0, paintGradient);

                        break;
                }


            }
//
//            Log.i("++++++++++++", "+++++++++++++++++++++++++++++++++++++++++)))))))))))))Hasan");

        }

    }


    private float[] generateCornerRadius(RectF rectangleRect, SkeletonAttribute skeletonAttributeChild) {

        //calculator cornerRadius size
        float cornerRadiusTopLeft;
        float cornerRadiusTopRight;
        float cornerRadiusBottomLRight;
        float cornerRadiusBottomLeft;

        if (skeletonAttributeChild.getCornerRadius() != Integer.MIN_VALUE) {
            cornerRadiusTopLeft = cornerRadiusTopRight = cornerRadiusBottomLRight = cornerRadiusBottomLeft = getCornerRadius(rectangleRect, skeletonAttributeChild.getCornerRadius());
        } else {
            cornerRadiusTopLeft = skeletonAttributeChild.getCornerRadiusTopLeft() != Integer.MIN_VALUE ? getCornerRadius(rectangleRect, skeletonAttributeChild.getCornerRadiusTopLeft()) : 0;
            cornerRadiusTopRight = skeletonAttributeChild.getCornerRadiusTopRight() != Integer.MIN_VALUE ? getCornerRadius(rectangleRect, skeletonAttributeChild.getCornerRadiusTopRight()) : 0;
            cornerRadiusBottomLRight = skeletonAttributeChild.getCornerRadiusBottomLRight() != Integer.MIN_VALUE ? getCornerRadius(rectangleRect, skeletonAttributeChild.getCornerRadiusBottomLRight()) : 0;
            cornerRadiusBottomLeft = skeletonAttributeChild.getCornerRadiusBottomLeft() != Integer.MIN_VALUE ? getCornerRadius(rectangleRect, skeletonAttributeChild.getCornerRadiusBottomLeft()) : 0;
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
        switch (skeletonAttribute.getAnimationDirection()) {
            case SkeletonAttribute.ANIMATION_DIRECTION_LTR: {
                float offset = getWidth() * AnimationFractionMove;
                xStartGradient = offset;
                yStartGradient = 0;
                xEndGradient = offset + getWidth();
                yEndGradient = 0;
                break;
            }
            case SkeletonAttribute.ANIMATION_DIRECTION_RTL: {
                float offset = getWidth() * AnimationFractionMove;
                xStartGradient = offset;
                yStartGradient = 0;
                xEndGradient = offset + getWidth();
                yEndGradient = 0;
                break;
            }
            case SkeletonAttribute.ANIMATION_DIRECTION_TTB: {
                float offset = getHeight() * AnimationFractionMove;
                xStartGradient = 0;
                yStartGradient = offset;
                xEndGradient = 0;
                yEndGradient = offset + getHeight();
                break;
            }
            case SkeletonAttribute.ANIMATION_DIRECTION_BTT: {
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

        if (skeletonAttributesChildren != null && skeletonAttributesChildren.size() > 0) {
            isCanDraw = true;
            isAnimationPlay = true;
            setHoldTouchEventsFromChildren(true); // enable Hold touchEvents from this and children
        } else {
            // SkeletonGroup no child extend from skeletonView
            return;
        }

        // Checking direction animation for generate value star and end move animation fraction
        float valueAnimationMoveFractionStart = 0;
        float valueAnimationMoveFractionEnd = 0;
        switch (skeletonAttribute.getAnimationDirection()) {
            case SkeletonAttribute.ANIMATION_DIRECTION_LTR:
                valueAnimationMoveFractionStart = -1;
                valueAnimationMoveFractionEnd = 1;
                break;
            case SkeletonAttribute.ANIMATION_DIRECTION_RTL:
                valueAnimationMoveFractionStart = 1;
                valueAnimationMoveFractionEnd = -1;
                break;
            case SkeletonAttribute.ANIMATION_DIRECTION_TTB:
                valueAnimationMoveFractionStart = -1;
                valueAnimationMoveFractionEnd = 1;
                break;
            case SkeletonAttribute.ANIMATION_DIRECTION_BTT:
                valueAnimationMoveFractionStart = 1;
                valueAnimationMoveFractionEnd = -1;
                break;
        }

        //initial animation
        valueAnimator = ValueAnimator.ofFloat(valueAnimationMoveFractionStart, valueAnimationMoveFractionEnd);
        valueAnimator.setDuration(skeletonAttribute.getAnimationDuration());
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
                CLog.i("SkeletonGroup onAnimationRepeat " + position);

                if (isCanDrawFinishState) {
                    CLog.i("SkeletonGroup isCanDrawFinishState " + position);
                    setupFinishingAnimation();
                }


                if (isLastLoopAnimation) {
                    // last repeat animation and start finish animation
                    CLog.i("SkeletonGroup isLastLoopAnimatio " + position);
                    if (skeletonAttribute.getAnimationFinishType() == SkeletonAttribute.ANIMATION_TYPE_NON) {
                        setupFinishingAnimation();
                    } else {
                        isLastLoopAnimation = false;
                        isCanDrawFinishState = true;
                        // Change color 011 (color one transparent = 0.0 | color two transparent = 1.0 | color three transparent = 1.0 )
                        gradientColors = ColorUtils.generateColorTransparent011(skeletonAttribute.getColorHighLight(), skeletonAttribute.getColorBackgroundViews());
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

        // Fire for listener
        if (skeletonListener != null)
            skeletonListener.onStartAnimation();

    }


    private void setupFinishingAnimation() {

        isCanDraw = false;
        isLastLoopAnimation = false;
        isCanDrawFinishState = false;


        // Remove animation listener
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
            valueAnimator.end();
            valueAnimator.cancel();
        }


        // Disable Hold touchEvents from this and children
        setHoldTouchEventsFromChildren(false);

        // Fire finish state for listener
        if (skeletonListener != null)
            skeletonListener.onFinishAnimation();

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
        skeletonAttribute.setAutoStartAnimation(isAnimationAutoStart);
    }

    public void setShowSkeleton(boolean isShowSkeleton) {
        skeletonAttribute.setShowSkeleton(isShowSkeleton);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        finishAnimation();
        Log.e("+++++++++" ,"onDetachedFromWindow" + position);
    }

    //==============================================================================================
    /* Listener */
    public interface SkeletonListener {
        void onStartAnimation();

        void onFinishAnimation();

    }

    public void setSkeletonListener(SkeletonListener skeletonListener) {
        this.skeletonListener = skeletonListener;
    }
    //==============================================================================================
}