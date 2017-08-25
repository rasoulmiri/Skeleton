package io.rmiri.placeholder_example.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import io.rmiri.placeholder.Master.PlaceholderDetail;
import io.rmiri.placeholder_example.Adapter.AdapterSample1;
import io.rmiri.placeholder_example.Data.DataObject;
import io.rmiri.placeholder_example.Data.GenarateDataFake;
import io.rmiri.placeholder_example.R;


public class Sample1Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterSample1 adapterSample1;
    private ArrayList<DataObject> dataObjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_1);


        //toolbar
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //initial recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        // initial PlaceholderDetail and set in adapter
        PlaceholderDetail placeholderDetail = new PlaceholderDetail();
        placeholderDetail.setPlaceholderIsOn(true);
        adapterSample1 = new AdapterSample1(getApplicationContext(), dataObjects, placeholderDetail);

        //set adapter in recyclerView
        recyclerView.setAdapter(adapterSample1);


        //after 5 second get data fake
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dataObjects = new GenarateDataFake().genarateDataFake();
                adapterSample1.addMoreDataAndPlaceholderFinish(dataObjects);
            }
        }, 5000);
    }


}
