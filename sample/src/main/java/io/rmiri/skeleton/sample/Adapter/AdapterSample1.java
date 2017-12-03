package io.rmiri.skeleton.sample.Adapter;


import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.rmiri.skeleton.Master.AdapterSkeleton;
import io.rmiri.skeleton.Master.IsCanSetAdapterListener;
import io.rmiri.skeleton.SkeletonGroup;
import io.rmiri.skeleton.sample.Data.DataObject;
import io.rmiri.skeleton.sample.R;


public class AdapterSample1 extends AdapterSkeleton<DataObject,AdapterSample1.ViewHolder> {


    public AdapterSample1(final Context context, final ArrayList<DataObject> items, final RecyclerView recyclerView, final IsCanSetAdapterListener isCanSetAdapterListener) {
        this.context = context;
        this.items = items;
        this.isCanSetAdapterListener = isCanSetAdapterListener;

        measureHeightRecyclerViewAndItem(recyclerView, R.layout.item_sample_1);// Set height

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample_1, parent, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private SkeletonGroup skeletonGroup;
        private AppCompatImageView photoACImgV;
        private TextView newFlagTv;
        private TextView titleTv;
        private TextView descriptionTv;
        private AppCompatImageButton addToParkingImgBtn;
        private AppCompatImageButton compareImgBtn;
        private TextView priceTv;


        ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            skeletonGroup = (SkeletonGroup) itemView.findViewById(R.id.skeletonGroup);
            photoACImgV = (AppCompatImageView) itemView.findViewById(R.id.photoACImgV);
            newFlagTv = (TextView) itemView.findViewById(R.id.newFlagTv);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            descriptionTv = (TextView) itemView.findViewById(R.id.descriptionTv);
            addToParkingImgBtn = (AppCompatImageButton) itemView.findViewById(R.id.addToParkingImgBtn);
            compareImgBtn = (AppCompatImageButton) itemView.findViewById(R.id.compareImgBtn);
            priceTv = (TextView) itemView.findViewById(R.id.priceTv);

        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.cardView.setPreventCornerOverlap(false);

//        holder.skeletonGroup.setPosition(position);//just for debug log

        if (skeletonConfig.isSkeletonIsOn()) {
            return;
        } else {
            holder.skeletonGroup.setShowSkeleton(false);
            holder.skeletonGroup.finishAnimation();
        }

        //set data in view
        final DataObject cardObj = items.get(position);

        holder.titleTv.setText(cardObj.getTitle());
        holder.descriptionTv.setText(cardObj.getDescription());
        holder.priceTv.setText(cardObj.getPrice());

        if (cardObj.isNew()) {
            holder.newFlagTv.setVisibility(View.VISIBLE);
        } else {
            holder.newFlagTv.setVisibility(View.GONE);
        }

        //set photo by Picasso lib
        Picasso.with(context).load(cardObj.getPhoto()).into(holder.photoACImgV);

    }



}
