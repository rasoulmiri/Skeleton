package io.rmiri.skeleton.sample.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.rmiri.skeleton.Master.AdapterSkeleton;
import io.rmiri.skeleton.Master.IsCanInitialSetAdapterListener;
import io.rmiri.skeleton.SkeletonGroup;
import io.rmiri.skeleton.sample.Data.DataObject;
import io.rmiri.skeleton.sample.R;


public class AdapterSample5 extends AdapterSkeleton<DataObject,AdapterSample5.ViewHolder> {



    public AdapterSample5(final Context context, final ArrayList<DataObject> items, final RecyclerView recyclerView, final IsCanInitialSetAdapterListener IsCanInitialSetAdapterListener) {
        this.context = context;
        this.items = items;
        this.isCanInitialSetAdapterListener = IsCanInitialSetAdapterListener;

        measureHeightRecyclerViewAndItem(recyclerView, R.layout.item_sample_5);// Set height

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
            return;
        } else {
            holder.skeletonGroup.setShowSkeleton(false);
            holder.skeletonGroup.finishAnimation();
        }

        // Set data in view
        final DataObject cardObj = items.get(position);

        holder.titleTv.setText(position + " " + cardObj.getTitle());

    }


}
