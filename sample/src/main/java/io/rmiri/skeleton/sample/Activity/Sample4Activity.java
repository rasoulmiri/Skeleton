package io.rmiri.skeleton.sample.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import io.rmiri.skeleton.SkeletonGroup;
import io.rmiri.skeleton.sample.R;


public class Sample4Activity extends AppCompatActivity {

    SkeletonGroup skeletonGroup;
    TextView textTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_4);

        // Toolbar
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        skeletonGroup = (SkeletonGroup) findViewById(R.id.skeletonGroup);
        textTv = (TextView) findViewById(R.id.textTv);
        skeletonGroup.setSkeletonListener(new SkeletonGroup.SkeletonListener() {
            @Override
            public void onStartAnimation() {

            }

            @Override
            public void onFinishAnimation() {
                textTv.setText("The Android O release ultimately became Android 8.0 Oreo, as predicted by pretty much everyone the first time they thought of a sweet");
            }
        });


        //after 5 second finish animation s
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                skeletonGroup.finishAnimation();
            }
        }, 5000);
    }


}
