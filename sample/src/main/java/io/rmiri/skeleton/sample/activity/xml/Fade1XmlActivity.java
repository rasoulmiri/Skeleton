package io.rmiri.skeleton.sample.activity.xml;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import io.rmiri.skeleton.master.IsCanSetAdapterListener;
import io.rmiri.skeleton.sample.adapter.xml.AdapterFadeXml1;
import io.rmiri.skeleton.sample.data.DataObject;
import io.rmiri.skeleton.sample.data.GeneratesDataFake;
import io.rmiri.skeleton.sample.R;


public class Fade1XmlActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterFadeXml1 adapterFadeXml1;
    private ArrayList<DataObject> dataObjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade_1_xml);


        // Toolbar
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Initial recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        // Set adapter in recyclerView
        adapterFadeXml1 = new AdapterFadeXml1(getApplicationContext(), dataObjects,recyclerView, new IsCanSetAdapterListener() {
            @Override
            public void isCanSet() {
                recyclerView.setAdapter(adapterFadeXml1);
            }
        });


        // After 5 second get data fake
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dataObjects = new GeneratesDataFake().generateDataFake();
                adapterFadeXml1.addMoreDataAndSkeletonFinish(dataObjects);
            }
        }, 5000);
    }


}
