package io.rmiri.skeleton.sample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import io.rmiri.skeleton.sample.R;
import io.rmiri.skeleton.sample.activity.java.addViewsByJava.AddViewsByJavaActivity;
import io.rmiri.skeleton.sample.activity.java.gradientJava.GradientJavaActivity;
import io.rmiri.skeleton.sample.activity.xml.autoItemsCountXml.AutoItemsCountXmlActivity;
import io.rmiri.skeleton.sample.activity.xml.fade1Xml.Fade1XmlActivity;
import io.rmiri.skeleton.sample.activity.xml.fade2Xml.FadeXml2Activity;
import io.rmiri.skeleton.sample.activity.xml.gradientXml.GradientXmlActivity;
import io.rmiri.skeleton.sample.activity.xml.shapeXml.ShapeXmlActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find view
        AppCompatButton sampleGradientXmlBtn = findViewById(R.id.sampleGradientXmlBtn);
        AppCompatButton sampleFadeXml1Btn = findViewById(R.id.sampleFadeXml1Btn);
        AppCompatButton sampleFadeXml2Btn = findViewById(R.id.sampleFadeXml2Btn);
        AppCompatButton sampleShapeXmlBtn = findViewById(R.id.sampleShapeXmlBtn);
        AppCompatButton sampleAutoItemsCountXmlBtn = findViewById(R.id.sampleAutoItemsCountXmlBtn);
        AppCompatButton sampleGradientJavaBtn = findViewById(R.id.sampleGradientJavaBtn);
        AppCompatButton sampleAddViewsByJavaBtn = findViewById(R.id.sampleAddViewsByJavaBtn);

        // Set on click listener for buttons
        sampleGradientXmlBtn.setOnClickListener(this);
        sampleFadeXml1Btn.setOnClickListener(this);
        sampleFadeXml2Btn.setOnClickListener(this);
        sampleShapeXmlBtn.setOnClickListener(this);
        sampleAutoItemsCountXmlBtn.setOnClickListener(this);
        sampleGradientJavaBtn.setOnClickListener(this);
        sampleAddViewsByJavaBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sampleGradientXmlBtn:
                startActivity(new Intent(this, GradientXmlActivity.class));
                break;
            case R.id.sampleFadeXml1Btn:
                startActivity(new Intent(this, Fade1XmlActivity.class));
                break;
            case R.id.sampleFadeXml2Btn:
                startActivity(new Intent(this, FadeXml2Activity.class));
                break;
            case R.id.sampleShapeXmlBtn:
                startActivity(new Intent(this, ShapeXmlActivity.class));
                break;
            case R.id.sampleAutoItemsCountXmlBtn:
                startActivity(new Intent(this, AutoItemsCountXmlActivity.class));
                break;
            case R.id.sampleGradientJavaBtn:
                startActivity(new Intent(this, GradientJavaActivity.class));
                break;
            case R.id.sampleAddViewsByJavaBtn:
                startActivity(new Intent(this, AddViewsByJavaActivity.class));
                break;
        }
    }
}
