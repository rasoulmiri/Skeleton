package io.rmiri.placeholder_example.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import io.rmiri.placeholder.PlaceholderGroup;
import io.rmiri.placeholder_example.R;


public class Sample4Activity extends AppCompatActivity {

    PlaceholderGroup placeholderGroup;
    TextView textTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_4);

        //toolbar
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        placeholderGroup = (PlaceholderGroup) findViewById(R.id.placeHolderGroup);
        textTv = (TextView) findViewById(R.id.textTv);
        placeholderGroup.setPlaceholderListener(new PlaceholderGroup.PlaceholderListener() {
            @Override
            public void onStartAnimation() {

            }

            @Override
            public void onFinishAnimation() {
                textTv.setText("The Android O release ultimately became Android 8.0 Oreo, as predicted by pretty much everyone the first time they thought of a sweet");
            }
        });


        //after 5 second finish animation placeholder
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                placeholderGroup.finishAnimation();
            }
        }, 5000);
    }


}
