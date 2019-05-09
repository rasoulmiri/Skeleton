package io.rmiri.skeleton.sample.activity.java.addViewsByJava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import io.rmiri.skeleton.SkeletonViewGroup;
import io.rmiri.skeleton.master.SkeletonModel;
import io.rmiri.skeleton.master.SkeletonModelBuilder;
import io.rmiri.skeleton.sample.R;
import io.rmiri.skeleton.utils.ConverterUnitUtil;


public class AddViewsByJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_views_by_java);

        // Toolbar
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        simpleSkeleton();
        startAndEndViewsSkeleton();
        customWidthAndHeightSkeleton();
        matchView();

    }

    void simpleSkeleton() {

        // Bind view
        RelativeLayout mainLayout1 = findViewById(R.id.mainLayout1);
        AppCompatImageButton btn1 = findViewById(R.id.btn1);
        AppCompatImageButton btn2 = findViewById(R.id.btn2);

        SkeletonViewGroup skeletonViewGroup = new SkeletonViewGroup(getApplicationContext());
        ArrayList<SkeletonModel> skeletonModels = new ArrayList<>();

        // Add Btn 1
        skeletonModels.add(new SkeletonModelBuilder()
                .setChildView(btn1) // AddView
                .build());

        // Add Btn 2
        skeletonModels.add(new SkeletonModelBuilder()
                .setChildView(btn2) // AddView
                .build());

        skeletonViewGroup.setSkeletonModels(skeletonModels);

        // Add SkeletonGroup
        ViewGroup.LayoutParams layout = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mainLayout1.addView(skeletonViewGroup, layout);
    }

    void startAndEndViewsSkeleton() {

        // Bind view
        RelativeLayout mainLayout2 = findViewById(R.id.mainLayout2);
        AppCompatImageButton btn3 = findViewById(R.id.btn3);
        AppCompatImageButton btn4 = findViewById(R.id.btn4);

        SkeletonViewGroup skeletonViewGroup = new SkeletonViewGroup(getApplicationContext());
        ArrayList<SkeletonModel> skeletonModels = new ArrayList<>();

        // Add Btn 1 and Btn 2
        skeletonModels.add(new SkeletonModelBuilder()
                .setStartView(btn3) // AddView start
                .setEndView(btn4)// AddView end
                .build());


        skeletonViewGroup.setSkeletonModels(skeletonModels);

        // Add SkeletonGroup
        ViewGroup.LayoutParams layout = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mainLayout2.addView(skeletonViewGroup, layout);
    }

    void customWidthAndHeightSkeleton() {

        // Bind view
        RelativeLayout mainLayout3 = findViewById(R.id.mainLayout3);
        AppCompatImageButton btn5 = findViewById(R.id.btn5);
        AppCompatImageButton btn6 = findViewById(R.id.btn6);

        SkeletonViewGroup skeletonViewGroup = new SkeletonViewGroup(getApplicationContext());
        ArrayList<SkeletonModel> skeletonModels = new ArrayList<>();

        // Add Btn 1
        skeletonModels.add(new SkeletonModelBuilder()
                .setChildView(btn5)// AddView
                .setCustomWidth(ConverterUnitUtil.dpToPx(getApplicationContext(), 140f)) // CustomWidth
                .setCustomHeight(ConverterUnitUtil.dpToPx(getApplicationContext(), 50f)) // CustomHeight
                .build());

        skeletonViewGroup.setSkeletonModels(skeletonModels);

        // Add SkeletonGroup
        ViewGroup.LayoutParams layout = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mainLayout3.addView(skeletonViewGroup, layout);
    }

    void matchView() {

        // Bind view
        RelativeLayout mainLayout4 = findViewById(R.id.mainLayout4);
        AppCompatImageButton btn7 = findViewById(R.id.btn7);
        AppCompatImageButton btn8 = findViewById(R.id.btn8);

        SkeletonViewGroup skeletonViewGroup = new SkeletonViewGroup(getApplicationContext());
        ArrayList<SkeletonModel> skeletonModels = new ArrayList<>();

        // Add Btn 1
        skeletonModels.add(new SkeletonModelBuilder()
                .setChildView(btn7)
                .setIsMatchViewBoolean(true) //MatchView
                .build());

        skeletonViewGroup.setSkeletonModels(skeletonModels);

        // Add SkeletonGroup
        ViewGroup.LayoutParams layout = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mainLayout4.addView(skeletonViewGroup, layout);
    }
}
