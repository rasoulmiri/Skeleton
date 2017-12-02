package io.rmiri.skeleton.sample.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import io.rmiri.skeleton.Master.SkeletonConfig;
import io.rmiri.skeleton.sample.Adapter.AdapterSample2;
import io.rmiri.skeleton.sample.Data.DataObject;
import io.rmiri.skeleton.sample.Data.GeneratesDataFake;
import io.rmiri.skeleton.sample.R;


public class Sample2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterSample2 adapterSample2;
    private ArrayList<DataObject> dataObjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_2);


        // Toolbar
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Initial recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        // initial SkeletonDetail and set in adapter
        SkeletonConfig skeletonConfig = new SkeletonConfig().build();
        adapterSample2 = new AdapterSample2(getApplicationContext(), dataObjects, skeletonConfig);

        //set adapter in recyclerView
        recyclerView.setAdapter(adapterSample2);


        // After 5 second get data fake
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dataObjects = new GeneratesDataFake().genarateDataFake();
                adapterSample2.addMoreDataAndSkeletonFinish(dataObjects);
            }
        }, 5000);
    }


}
