package io.rmiri.skeleton.sample.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.rmiri.skeleton.Master.SkeletonConfig;
import io.rmiri.skeleton.SkeletonGroup;
import io.rmiri.skeleton.sample.Data.DataObject;
import io.rmiri.skeleton.sample.R;
import io.rmiri.skeleton.utils.CLog;


public class AdapterSample5 extends RecyclerView.Adapter<AdapterSample5.ViewHolder> {

    private Context context;
    private ArrayList<DataObject> dataObjects = new ArrayList<>();
    private SkeletonConfig skeletonConfig = new SkeletonConfig().build();


    public AdapterSample5(final Context context, final ArrayList<DataObject> dataObjects, final RecyclerView recyclerView, final isCanInitialSetAdapterListener isCanInitialSetAdapterListener) {
        this.context = context;
        this.dataObjects = dataObjects;

        ViewTreeObserver viewTreeObserver = recyclerView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                // Initial SkeletonDetail and set in adapter
                skeletonConfig.setRecyclerViewHeight(recyclerView.getHeight());// Height recyclerView

                View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_sample_5, null);
                view.getRootView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                skeletonConfig.setItemHeight(view.getRootView().getMeasuredHeight());// Height Item
                skeletonConfig.setNumberItemShow(Math.round(skeletonConfig.getRecyclerViewHeight() / skeletonConfig.getItemHeight()) + 1); // Number item skeleton in adapter

                CLog.i("skeletonConfig.getItemHeight == " + skeletonConfig.getItemHeight()
                        + "   skeletonConfig.getRecyclerViewHeight  " + skeletonConfig.getRecyclerViewHeight()
                        + "   skeletonConfig.getNumberItemShow  " + skeletonConfig.getNumberItemShow());

                // Remove ViewTreeObserver
                ViewTreeObserver obs = recyclerView.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                isCanInitialSetAdapterListener.isCan();
            }
        });


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample_5, parent, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private SkeletonGroup skeletonGroup;
        private TextView titleTv;

        ViewHolder(View itemView) {
            super(itemView);
            skeletonGroup = (SkeletonGroup) itemView.findViewById(R.id.skeletonGroup);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.skeletonGroup.setPosition(position);//just for debug log

        if (skeletonConfig.isSkeletonIsOn()) {
            holder.skeletonGroup.setAutoPlay(true);
            return;
        } else {
            holder.skeletonGroup.setAutoPlay(true);
            holder.skeletonGroup.setShowSkeleton(false);
            holder.skeletonGroup.finishAnimation();
        }

        // Set data in view
        final DataObject cardObj = dataObjects.get(position);

        holder.titleTv.setText(position + " " + cardObj.getTitle());

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        if (payloads != null && !payloads.isEmpty()) {
            // Just for notifyItemChanged by payload
            holder.skeletonGroup.setShowSkeleton(false);
            holder.skeletonGroup.finishAnimation();
            super.onBindViewHolder(holder, position, payloads);
        } else {
            super.onBindViewHolder(holder, position, payloads);
        }
    }

    @Override
    public int getItemCount() {
        if (skeletonConfig.isSkeletonIsOn()) {
            if (skeletonConfig.getItemHeight() == 0) {
                CLog.i("getItemCount ==> getItemHeight() is zero : " + "1");
                return 1;
            } else {
                CLog.i("getItemCount ==> getNumberItemShow: " + skeletonConfig.getNumberItemShow());
                return skeletonConfig.getNumberItemShow();
            }
        } else {
            CLog.i("getItemCount ==> dataObjects.size(): " + dataObjects.size());
            return dataObjects.size();
        }
    }


    public void addMoreDataAndSkeletonFinish(ArrayList<DataObject> dataObjects) {

        // Add new data to dataObjects
        this.dataObjects = new ArrayList<>();
        this.dataObjects.addAll(dataObjects);

        // Set false show Skeleton
        skeletonConfig.setSkeletonIsOn(false);

        // Update data for skeleton
        for (int i = 0; i < skeletonConfig.getNumberItemShow(); i++) {
            notifyItemChanged(i, 1);
        }

    }

    public interface isCanInitialSetAdapterListener {
        public void isCan();
    }

}
