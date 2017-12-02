package io.rmiri.skeleton.sample.Adapter;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.rmiri.skeleton.Master.SkeletonConfig;
import io.rmiri.skeleton.SkeletonGroup;
import io.rmiri.skeleton.sample.Data.DataObject;
import io.rmiri.skeleton.sample.R;


public class AdapterSample5 extends RecyclerView.Adapter<AdapterSample5.ViewHolder> {

    private Context context;
    private ArrayList<DataObject> dataObjects = new ArrayList<>();
    private SkeletonConfig skeletonConfig;

    public AdapterSample5(Context context, ArrayList<DataObject> dataObjects, @Nullable SkeletonConfig skeletonConfig) {
        this.context = context;
        this.dataObjects = dataObjects;
        if (skeletonConfig != null) {
            this.skeletonConfig = skeletonConfig;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample_5, parent, false);


        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private SkeletonGroup skeletonGroup;
        private TextView titleTv;


        ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            skeletonGroup = (SkeletonGroup) itemView.findViewById(R.id.skeletonGroup);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);


            if (skeletonConfig.getItemHeight() == 0) {
                cardView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                skeletonConfig.setItemHeight(cardView.getMeasuredHeight());
//            recyclerViewHeight = mRecyclerView.getMeasuredHeight();

                Log.i("+++++++++++++++____++++++", "onCreateViewHolder itemHeight333 ========> " + skeletonConfig.getItemHeight() + "    " + skeletonConfig.getRecyclerViewHeight());

            }

        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.cardView.setPreventCornerOverlap(false);

//        holder.skeletonGroup.setPosition(position);//just for debug log

        if (skeletonConfig.isSkeletonIsOn()) {
            //need show s for 2 cards
            holder.skeletonGroup.setAutoPlay(true);
            return;
        } else {
            holder.skeletonGroup.setShowSkeleton(false);
            holder.skeletonGroup.finishAnimation();
        }

        //set data in view
        final DataObject cardObj = dataObjects.get(position);

        holder.titleTv.setText(cardObj.getTitle());


    }

    @Override
    public int getItemCount() {
//        if (skeletonConfig.isSkeletonIsOn()) {
//            // show just 2 card item in recyclerView
//            return 2;
//        } else {
//            //normal show card item in recyclerView
//            return dataObjects.size();
//        }


        Log.i("+++++++++++++++____++++++", "getItemCount");
        if (skeletonConfig.isSkeletonIsOn()) {
            if (skeletonConfig.getItemHeight() == 0) {
                Log.i("+++++++++++++++____++++++", "getItemCount" + "1");
                return 8;
            } else {
                Log.i("+++++++++++++++____++++++", "getItemCount" + skeletonConfig.getRecyclerViewHeight() / skeletonConfig.getItemHeight());
                return (int) skeletonConfig.getRecyclerViewHeight() / skeletonConfig.getItemHeight();
            }
        } else {
            Log.i("+++++++++++++++____++++++", "getItemCount  Sizeeeeeeeee" + dataObjects.size());
            return dataObjects.size();
        }
    }


    public void addMoreDataAndSkeletonFinish(ArrayList<DataObject> dataObjects) {

        //add new data to dataObjects
        this.dataObjects = new ArrayList<>();
        this.dataObjects.addAll(dataObjects);

        //set false show s
        skeletonConfig.setSkeletonIsOn(false);

        //update items cardView
        notifyDataSetChanged();
    }


}
