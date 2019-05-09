package io.rmiri.skeleton.sample.activity.xml.autoItemsCountXml;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.rmiri.skeleton.SkeletonViewGroup;
import io.rmiri.skeleton.master.AdapterSkeleton;
import io.rmiri.skeleton.master.IsCanSetAdapterListener;
import io.rmiri.skeleton.sample.R;
import io.rmiri.skeleton.sample.data.DataObject;


public class AutoItemsCountXmlAdapter extends AdapterSkeleton<DataObject, AutoItemsCountXmlAdapter.ViewHolder> {

    public AutoItemsCountXmlAdapter(final Context context, final ArrayList<DataObject> items, final RecyclerView recyclerView, final IsCanSetAdapterListener IsCanSetAdapterListener) {
        this.context = context;
        this.items = items;
        this.isCanSetAdapterListener = IsCanSetAdapterListener;

        measureHeightRecyclerViewAndItem(recyclerView, R.layout.item_auto_items_count_xml);// Set height

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_auto_items_count_xml, parent, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final SkeletonViewGroup skeletonViewGroup;
        private final TextView titleTv;

        ViewHolder(View itemView) {
            super(itemView);
            skeletonViewGroup = itemView.findViewById(R.id.skeletonGroup);
            titleTv = itemView.findViewById(R.id.titleTv);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.skeletonViewGroup.setPosition(position);//just for debug log

        if (skeletonConfig.isSkeletonIsOn()) {
            return;
        } else {
            holder.skeletonViewGroup.setShowSkeleton(false);
            holder.skeletonViewGroup.finishAnimation();
        }

        // Set data in view
        final DataObject cardObj = items.get(position);

        holder.titleTv.setText(position + " " + cardObj.getTitle());

    }


}
