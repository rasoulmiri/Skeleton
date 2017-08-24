package io.rmiri.placeholder_example;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import io.rmiri.placeholder.Master.PlaceholderDetail;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<DataObject> dataObjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initial recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        // initial PlaceholderDetail and set in adapter
        PlaceholderDetail placeholderDetail = new PlaceholderDetail();
        placeholderDetail.setPlaceholderIsOn(true);
        recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(),dataObjects, placeholderDetail);

        //set adapter in recyclerView
        recyclerView.setAdapter(recyclerViewAdapter);


        //after 5 second get data fake
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dataObjects =   new GenarateDataFake().genarateDataFake();
                recyclerViewAdapter.addMoreDataAndPlaceholderFinish(dataObjects);
            }
        }, 5000);
    }


}
