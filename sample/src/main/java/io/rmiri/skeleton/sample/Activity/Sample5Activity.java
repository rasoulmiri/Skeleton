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
import io.rmiri.skeleton.sample.Adapter.AdapterSample5;
import io.rmiri.skeleton.sample.Data.DataObject;
import io.rmiri.skeleton.sample.Data.GeneratesDataFake;
import io.rmiri.skeleton.sample.R;


public class Sample5Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterSample5 adapterSample5;
    private ArrayList<DataObject> dataObjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_5);

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


        // Initial adapter for skeleton
//        ViewTreeObserver viewTreeObserver = recyclerView.getViewTreeObserver();
//        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//
//                // Initial SkeletonDetail and set in adapter
//                SkeletonConfig skeletonConfig = new SkeletonConfig().build();
//                skeletonConfig.setRecyclerViewHeight(recyclerView.getHeight());// Height recyclerView
//
//                View view = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_sample_5, null);
//                view.getRootView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//                skeletonConfig.setItemHeight(view.getRootView().getMeasuredHeight());// Height Item
//                skeletonConfig.setNumberItemShow(skeletonConfig.getRecyclerViewHeight() / skeletonConfig.getItemHeight()); // Number item skeleton in adapter
//
//                CLog.i("skeletonConfig.getItemHeight == " + skeletonConfig.getItemHeight()
//                        + "   skeletonConfig.getRecyclerViewHeight  " + skeletonConfig.getRecyclerViewHeight()
//                        + "   skeletonConfig.getNumberItemShow  " + skeletonConfig.getNumberItemShow());
//
//
//                // Set adapter in recyclerView
//                adapterSample5 = new AdapterSample5(getApplicationContext(), dataObjects, skeletonConfig);
//                recyclerView.setAdapter(adapterSample5);
//
//
//                // Remove ViewTreeObserver
//                ViewTreeObserver obs = recyclerView.getViewTreeObserver();
//                obs.removeGlobalOnLayoutListener(this);
//            }
//        });
//


        // Set adapter in recyclerView
        adapterSample5 = new AdapterSample5(getApplicationContext(), dataObjects,recyclerView, new AdapterSample5.isCanInitialSetAdapterListener() {
            @Override
            public void isCan() {
                recyclerView.setAdapter(adapterSample5);
            }
        });


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {

//            }
//        }, 3000); // After 5 second get data fake


        // After 5 second get data fake
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dataObjects = new GeneratesDataFake().genarateDataFake();
                adapterSample5.addMoreDataAndSkeletonFinish(dataObjects);
            }
        }, 5000); // After 5 second get data fake

    }


}
