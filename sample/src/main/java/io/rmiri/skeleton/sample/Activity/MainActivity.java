package io.rmiri.skeleton.sample.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import io.rmiri.skeleton.sample.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton sample1Btn, sample2Btn, sample3Btn, sample4Btn, sample5Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find view
        sample1Btn = (AppCompatButton) findViewById(R.id.sample1Btn);
        sample2Btn = (AppCompatButton) findViewById(R.id.sample2Btn);
        sample3Btn = (AppCompatButton) findViewById(R.id.sample3Btn);
        sample4Btn = (AppCompatButton) findViewById(R.id.sample4Btn);

        //set on click listener for buttons
        sample1Btn.setOnClickListener(this);
        sample2Btn.setOnClickListener(this);
        sample3Btn.setOnClickListener(this);
        sample4Btn.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sample1Btn:
                startActivity(new Intent(this, Sample1Activity.class));
                break;
            case R.id.sample2Btn:
                startActivity(new Intent(this, Sample2Activity.class));
                break;
            case R.id.sample3Btn:
                startActivity(new Intent(this, Sample3Activity.class));
                break;
            case R.id.sample4Btn:
                startActivity(new Intent(this, Sample4Activity.class));
                break;
        }
    }
}
