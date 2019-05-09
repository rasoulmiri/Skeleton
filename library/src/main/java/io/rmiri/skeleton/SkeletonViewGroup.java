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
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.rmiri.skeleton.master.SkeletonMaster;
import io.rmiri.skeleton.master.SkeletonModel;
import io.rmiri.skeleton.utils.CLog;
import io.rmiri.skeleton.utils.ColorUtils;

import static io.rmiri.skeleton.master.SkeletonModel.ANIMATION_DIRECTION_BTT;
import static io.rmiri.skeleton.master.SkeletonModel.ANIMATION_DIRECTION_LTR;
import static io.rmiri.skeleton.master.SkeletonModel.ANIMATION_DIRECTION_RTL;
import static io.rmiri.skeleton.master.SkeletonModel.ANIMATION_DIRECTION_TTB;
import static io.rmiri.skeleton.master.SkeletonModel.ANIMATION_TYPE_ALPHA;
import static io.rmiri.skeleton.master.SkeletonModel.ANIMATION_TYPE_GRADIENT;
import static io.rmiri.skeleton.master.SkeletonModel.ANIMATION_TYPE_NON;
import static io.rmiri.skeleton.master.SkeletonModel.SHAPE_TYPE_OVAL;
import static io.rmiri.skeleton.master.SkeletonModel.SHAPE_TYPE_RECT;
import static io.rmiri.skeleton.master.SkeletonModel.SHAPE_TYPE_TEXT;
import static io.rmiri.skeleton.master.SkeletonModel.TEXT_SHAPE_LINE_LAST_WIDTH_FULL;
import static io.rmiri.skeleton.master.SkeletonModel.TEXT_SHAPE_LINE_LAST_WIDTH_HALF;
import static io.rmiri.skeleton.master.SkeletonModel.TEXT_SHAPE_LINE_LAST_WIDTH_QUARTER;
import static io.rmiri.skeleton.master.SkeletonModel.TEXT_SHAPE_LINE_LAST_WIDTH_THREE_QUARTERS;


/**
 * Created by Rasoul Miri on 8/8/17.
 */

public class SkeletonViewGroup extends SkeletonMaster {

    ArrayList<SkeletonModel> skeletonModels = new ArrayList<>(); // Array for all children (add by java)
    private ArrayList<SkeletonModel> skeletonModelsChildren;// Array for all children extend from SkeletonView (add by xml)

    private SkeletonListener skeletonListener; // Listener

    int parentId = -1; // This use for loop for x,y real

    // Variable for draw canvas
    private Paint paintGradient;
    private Paint paintBackgroundViews;
    private Canvas canvasGradientElement;
    private Canvas canvasBackgroundElement;
    private Bitmap bitmapGradientElement;
    private Bitmap bitmapBackgroundElement;
    private Path path = new Path();

    // Variable for gradient
    private int[] gradientColors; // Is 3 colors
    private float xStartGradient, yStartGradient, xEndGradient, yEndGradient;

    // Variable for fraction animation played
    private ValueAnimator valueAnimator;
    private float AnimationFractionMove = 0.0f;
    private float AnimationFraction = 0.0f;

    // Variable for checking
    private boolean isCanDraw = false;
    private boolean isCanDrawFinishState = false;
    private boolean isAnimationPlay = false;
    private boolean isLastLoopAnimation = false;

    public SkeletonViewGroup(@NonNull Context context) {
        super(context);
    }

    public SkeletonViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SkeletonViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SkeletonViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void init(Context context, @Nullable AttributeSet attrs) {
        super.init(context, attrs);

        // Generate color 010 (color one transparent = 0.0 | color two transparent = 1.0 | color three transparent = 1.0 )
        gradientColors = ColorUtils.generateColorTransparent010(skeletonModel.getColorHighLight(), skeletonModel.getColorBackgroundViews());

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        CLog.i("SkeletonGroup onLayout " + position);

        if (skeletonModelsChildren == null
                && skeletonModels != null
                && skeletonModels.size() > 0
                && skeletonModel.isShowSkeleton()) {

            CLog.i("SkeletonGroup onLayout and skeletonModelsChildren == null ... " + position);

            skeletonModelsChildren = new ArrayList<>();

            // set id for parent if not id
            if (getParent() instanceof ViewGroup) {
                parentId = ((ViewGroup) getParent()).getId();
                if (parentId == -1) {
                    Log.e("SkeletonView", "Parent need to ID");
                    ((ViewGroup) getParent()).setId(View.generateViewId());
                    parentId = ((ViewGroup) getParent()).getId();
                }
            }


            for (SkeletonModel item : skeletonModels) {

                if (item.getStartView() != null) {
                    Log.i("SkeletonView", "use start and end views");
                    item.setChildView(item.getStartView());
                }

                // Set point X1,Y1,X2,Y2
                float X1 = item.getChildView().getLeft();
                float Y1 = item.getChildView().getTop();
                float X2 = 0.0f;
                float Y2 = 0.0f;

                // Calculator X1,X2 to left and top SkeletonGroup view
                Pair<Float, Float> pair = calculateXAndYReal(item.getChildView());
                X1 += pair.first;
                Y1 += pair.second;

                // Calculator padding size
                float pTop;
                float pLeft;
                float pRight;
                float pBottom;

                if (item.getPadding() != Float.MIN_VALUE) {
                    pTop = pLeft = pRight = pBottom = item.getPadding();
                } else {
                    pTop = item.getPaddingTop() != Float.MIN_VALUE ? item.getPaddingTop() : 0;
                    pLeft = item.getPaddingLeft() != Float.MIN_VALUE ? item.getPaddingLeft() : 0;
                    pRight = item.getPaddingRight() != Float.MIN_VALUE ? item.getPaddingRight() : 0;
                    pBottom = item.getPaddingBottom() != Float.MIN_VALUE ? item.getPaddingBottom() : 0;
                }

                if (item.isMatchViewBoolean()) {
                    X2 = X1 + getMeasuredWidth() - pRight;
                    Y2 = Y1 + getMeasuredHeight() - pBottom;
                } else {
                    if (item.getEndView() != null) {
                        // Use startView and endView instead of real childView width

                        float X1End = (float) item.getEndView().getLeft();
                        float Y1End = (float) item.getEndView().getTop();

                        Pair<Float, Float> pairXY = calculateXAndYReal(item.getEndView());
                        X2 = X1End + pairXY.first + item.getEndView().getMeasuredWidth() - pRight;
                        Y2 = Y1End + pairXY.second + item.getEndView().getMeasuredHeight() - pBottom;
                    } else if (item.getCustomWidth() != Float.MIN_VALUE) {
                        // Use customWidth and customHeight instead of real childView width and height
                        X2 = X1 + item.getCustomWidth() - pRight;
                        Y2 = Y1 + item.getCustomHeight() - pBottom;
                    } else {
                        X2 = X1 + (item.getChildView().getMeasuredWidth()) - pRight;
                        Y2 = Y1 + (item.getChildView().getMeasuredHeight()) - pBottom;
                    }
                }

                X1 = X1 + pLeft;
                Y1 = Y1 + pTop;

                item.setX1(X1);
                item.setY1(Y1);
                item.setX2(X2);
                item.setY2(Y2);

                skeletonModelsChildren.add(item);

            }
            if (skeletonModel.isAutoStartAnimation()) {
                setupInitialAnimation();
            }

        } else if (skeletonModelsChildren == null && skeletonModel.isShowSkeleton()) {

            CLog.i("SkeletonGroup onLayout and skeletonModelsChildren == null ... " + position);
            // Add all children extend from SkeletonView

            skeletonModelsChildren = new ArrayList<>();

            for (View child : getAllChildren(getChildAt(0))) {

                if (child != null && child instanceof SkeletonView) {

                    SkeletonModel item = ((SkeletonView) child).getSkeletonModel();

                    // Set point X1,Y1,X2,Y2
                    float X1 = child.getLeft();
                    float Y1 = child.getTop();
                    float X2 = 0.0f;
                    float Y2 = 0.0f;

                    // Calculator X1,X2 to left and top SkeletonGroup view
                    View parent = (View) child.getParent();
                    while (parent != null && !(parent instanceof SkeletonViewGroup)) {
                        X1 += parent.getLeft();
                        Y1 += parent.getTop();
                        parent = (View) parent.getParent();
                    }

                    // Calculator padding size
                    float pTop;
                    float pLeft;
                    float pRight;
                    float pBottom;

                    if (item.getPadding() != Integer.MIN_VALUE) {
                        pTop = pLeft = pRight = pBottom = item.getPadding();
                    } else {
                        pTop = item.getPaddingTop() != Integer.MIN_VALUE ? item.getPaddingTop() : 0;
                        pLeft = item.getPaddingLeft() != Integer.MIN_VALUE ? item.getPaddingLeft() : 0;
                        pRight = item.getPaddingRight() != Integer.MIN_VALUE ? item.getPaddingRight() : 0;
                        pBottom = item.getPaddingBottom() != Integer.MIN_VALUE ? item.getPaddingBottom() : 0;
                    }

                    X2 = X1 + child.getMeasuredWidth() - pRight;
                    Y2 = Y1 + child.getMeasuredHeight() - pBottom;
                    X1 = X1 + pLeft;
                    Y1 = Y1 + pTop;

                    item.setX1(X1);
                    item.setY1(Y1);
                    item.setX2(X2);
                    item.setY2(Y2);


                    skeletonModelsChildren.add(item);
                }
            }
            if (skeletonModel.isAutoStartAnimation()) {
                setupInitialAnimation();
            }
        }
    }


    private Pair<Float, Float> calculateXAndYReal(View view) {

        if (view == null) {
            Log.i("SkeletonView", "view is null");
            return new Pair(0f, 0f);
        }
        float X = 0.0f;
        float Y = 0.0f;

        // Calculator X1,Y1 to left and top SkeletonGroup view
        View parent = (View) view.getParent();
        while (parent != null && parent.getId() != parentId) {
            X += parent.getLeft();
            Y += parent.getTop();
            parent = (View) parent.getParent();
        }

        // remove padding left and top parent
        View parentViewMaster = ((View) getParent());
        X = X - parentViewMaster.getPaddingLeft();
        Y = Y - parentViewMaster.getPaddingTop();


        return new Pair(X, Y);

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if (isCanDraw) {

            // Just once run this code
            // Calculate bound child for draw
            if (paintGradient == null) {

                //paint
                paintBackgroundViews = new Paint(Paint.ANTI_ALIAS_FLAG);
                paintBackgroundViews.setStyle(Paint.Style.FILL);
                paintBackgroundViews.setColor(skeletonModel.getColorBackgroundViews());
                paintGradient = new Paint(Paint.ANTI_ALIAS_FLAG);
                paintGradient.setStyle(Paint.Style.FILL);

                //canvas
                Bitmap.Config bitmapConfig = Bitmap.Config.ALPHA_8;
                bitmapBackgroundElement = Bitmap.createBitmap(getWidth(), getHeight(), bitmapConfig);
                canvasBackgroundElement = new Canvas(bitmapBackgroundElement);
                bitmapGradientElement = Bitmap.createBitmap(getWidth(), getHeight(), bitmapConfig);
                canvasGradientElement = new Canvas(bitmapGradientElement);

                for (int i = 0; i < skeletonModelsChildren.size(); i++) {

                    SkeletonModel skeletonAttributeChild = skeletonModelsChildren.get(i);

                    if (skeletonAttributeChild.getShapeType() == SHAPE_TYPE_RECT) {
                        // Rectangle
                        RectF rectangleRect = new RectF(skeletonAttributeChild.getX1(), skeletonAttributeChild.getY1(), skeletonAttributeChild.getX2(), skeletonAttributeChild.getY2());
                        float[] cornerRadius = generateCornerRadius(rectangleRect, skeletonAttributeChild);
                        path.addRoundRect(rectangleRect, cornerRadius, Path.Direction.CW);
                    } else if (skeletonAttributeChild.getShapeType() == SHAPE_TYPE_OVAL) {
                        // Oval
                        RectF ovalRect = new RectF(skeletonAttributeChild.getX1(), skeletonAttributeChild.getY1(), skeletonAttributeChild.getX2(), skeletonAttributeChild.getY2());
                        path.addOval(ovalRect, Path.Direction.CW);
                    } else if (skeletonAttributeChild.getShapeType() == SHAPE_TYPE_TEXT) {
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
                                    case TEXT_SHAPE_LINE_LAST_WIDTH_FULL:
                                        // No change X1 and X2
                                        break;
                                    case TEXT_SHAPE_LINE_LAST_WIDTH_THREE_QUARTERS:
                                        if (skeletonModel.getAnimationDirection() == ANIMATION_DIRECTION_RTL) {
                                            newX1 = skeletonAttributeChild.getX1() + quarterWidth;
                                        } else {
                                            newX2 = skeletonAttributeChild.getX2() - quarterWidth;
                                        }
                                        break;
                                    case TEXT_SHAPE_LINE_LAST_WIDTH_HALF:
                                        if (skeletonModel.getAnimationDirection() == ANIMATION_DIRECTION_RTL) {
                                            newX1 = skeletonAttributeChild.getX1() + (2 * quarterWidth);
                                        } else {
                                            newX2 = skeletonAttributeChild.getX2() - (2 * quarterWidth);
                                        }
                                        break;
                                    case TEXT_SHAPE_LINE_LAST_WIDTH_QUARTER:
                                        if (skeletonModel.getAnimationDirection() == ANIMATION_DIRECTION_RTL) {
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
                switch (skeletonModel.getAnimationFinishType()) {
                    case ANIMATION_TYPE_ALPHA:

                        // Draw background total view with alpha
                        drawBackgroundTotal(canvas, ColorUtils.convertColorToTransparent(skeletonModel.getColorBackgroundMain(), 1.0f - AnimationFraction));

                        paintBackgroundViews.setAlpha((int) (255 * (1.0f - AnimationFraction)));
                        canvas.drawBitmap(bitmapBackgroundElement, 0, 0, paintBackgroundViews);

                        break;
                    case ANIMATION_TYPE_GRADIENT:

                        // Draw background total view with alpha
                        // drawBackgroundTotal(canvas, ColorUtils.convertColorToTransparent(skeletonModel.getColorBackgroundMain(), 1.0f - AnimationFraction));

                        if (AnimationFractionMove == 1f || AnimationFractionMove == -1f) {
                            drawBackgroundTotal(canvas, skeletonModel.getColorBackgroundMain());//draw background total
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

                drawBackgroundTotal(canvas, skeletonModel.getColorBackgroundMain());//draw background total
                canvas.drawBitmap(bitmapBackgroundElement, 0, 0, paintBackgroundViews);//draw background view
                switch (skeletonModel.getAnimationNormalType()) {

                    case ANIMATION_TYPE_ALPHA:

                        float alpha = 0.0f;
//                        if (AnimationFractionMove >= 0) {
//                            alpha = AnimationFractionMove;
//                        } else {
//                            alpha = AnimationFractionMove + 1;
//                        }
                        CLog.i(alpha + "");
                        paintGradient.setColor(ColorUtils.convertColorToTransparent(skeletonModel.getColorHighLight(), Math.abs(AnimationFractionMove)));

                        canvas.drawBitmap(bitmapGradientElement, 0, 0, paintGradient);

                        break;
                    case ANIMATION_TYPE_GRADIENT:

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


    private float[] generateCornerRadius(RectF rectangleRect, SkeletonModel skeletonAttributeChild) {

        // Calculator cornerRadius size
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
        // For transparent no need draw color
        if (color != android.R.color.transparent) {
            canvas.drawColor(color);// Background total view
        }
    }

    private void generatePositionGradientFromDirectionAnimation() {

        // Check direction and set x,y start and end for gradient
        switch (skeletonModel.getAnimationDirection()) {
            case ANIMATION_DIRECTION_LTR: {
                float offset = getWidth() * AnimationFractionMove;
                xStartGradient = offset;
                yStartGradient = 0;
                xEndGradient = offset + getWidth();
                yEndGradient = 0;
                break;
            }
            case ANIMATION_DIRECTION_RTL: {
                float offset = getWidth() * AnimationFractionMove;
                xStartGradient = offset;
                yStartGradient = 0;
                xEndGradient = offset + getWidth();
                yEndGradient = 0;
                break;
            }
            case ANIMATION_DIRECTION_TTB: {
                float offset = getHeight() * AnimationFractionMove;
                xStartGradient = 0;
                yStartGradient = offset;
                xEndGradient = 0;
                yEndGradient = offset + getHeight();
                break;
            }
            case ANIMATION_DIRECTION_BTT: {
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

        if (skeletonModelsChildren != null && skeletonModelsChildren.size() > 0) {
            isCanDraw = true;
            isAnimationPlay = true;
            setHoldTouchEventsFromChildren(true); // Enable Hold touchEvents from this and children
        } else {
            // SkeletonGroup no child extend from skeletonView
            return;
        }

        // Checking direction animation for generate value star and end move animation fraction
        float valueAnimationMoveFractionStart = 0;
        float valueAnimationMoveFractionEnd = 0;
        switch (skeletonModel.getAnimationDirection()) {
            case ANIMATION_DIRECTION_LTR:
                valueAnimationMoveFractionStart = -1;
                valueAnimationMoveFractionEnd = 1;
                break;
            case ANIMATION_DIRECTION_RTL:
                valueAnimationMoveFractionStart = 1;
                valueAnimationMoveFractionEnd = -1;
                break;
            case ANIMATION_DIRECTION_TTB:
                valueAnimationMoveFractionStart = -1;
                valueAnimationMoveFractionEnd = 1;
                break;
            case ANIMATION_DIRECTION_BTT:
                valueAnimationMoveFractionStart = 1;
                valueAnimationMoveFractionEnd = -1;
                break;
        }

        // Initial animation
        valueAnimator = ValueAnimator.ofFloat(valueAnimationMoveFractionStart, valueAnimationMoveFractionEnd);
        valueAnimator.setDuration(skeletonModel.getAnimationDuration());
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
                    // Last repeat animation and start finish animation
                    CLog.i("SkeletonGroup isLastLoopAnimation " + position);
                    if (skeletonModel.getAnimationFinishType() == ANIMATION_TYPE_NON) {
                        setupFinishingAnimation();
                    } else {
                        isLastLoopAnimation = false;
                        isCanDrawFinishState = true;
                        // Change color 011 (color one transparent = 0.0 | color two transparent = 1.0 | color three transparent = 1.0 )
                        gradientColors = ColorUtils.generateColorTransparent011(skeletonModel.getColorHighLight(), skeletonModel.getColorBackgroundViews());
                    }
                }
            }
        });

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                AnimationFractionMove = (float) (Float) animation.getAnimatedValue();
                AnimationFraction = animation.getAnimatedFraction();
                invalidate();
            }
        });

        valueAnimator.start();

        // Call start animation in listener
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

        // Call finish state in listener
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

    public void setSkeletonModels(ArrayList<SkeletonModel> skeletonModels) {
        this.skeletonModels = skeletonModels;
    }

    public void setAutoPlay(boolean isAnimationAutoStart) {
        skeletonModel.setAutoStartAnimation(isAnimationAutoStart);
    }

    public void setShowSkeleton(boolean isShowSkeleton) {
        skeletonModel.setShowSkeleton(isShowSkeleton);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        finishAnimation();
        Log.e("+++++++++", "onDetachedFromWindow" + position);
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