package io.rmiri.skeleton.sample.activity.xml;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import io.rmiri.skeleton.SkeletonViewGroup;
import io.rmiri.skeleton.sample.R;


public class ShapeXmlActivity extends AppCompatActivity {

    private SkeletonViewGroup skeletonViewGroup;
    private TextView textTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_xml);

        // Toolbar
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        skeletonViewGroup = findViewById(R.id.skeletonGroup);
        textTv = findViewById(R.id.textTv);
        skeletonViewGroup.setSkeletonListener(new SkeletonViewGroup.SkeletonListener() {
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
                skeletonViewGroup.finishAnimation();
            }
        }, 5000);
    }


}
