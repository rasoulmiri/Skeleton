package io.rmiri.placeholder_example.Adapter;


import android.content.Context;
import android.support.annotation.Nullable;
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

import io.rmiri.placeholder.Master.PlaceholderDetail;
import io.rmiri.placeholder.PlaceholderGroup;
import io.rmiri.placeholder_example.Data.DataObject;
import io.rmiri.placeholder_example.R;


public class AdapterSample2 extends RecyclerView.Adapter<AdapterSample2.ViewHolder> {

    private Context context;
    private ArrayList<DataObject> dataObjects = new ArrayList<>();
    private PlaceholderDetail placeholderDetail = new PlaceholderDetail();

    public AdapterSample2(Context context, ArrayList<DataObject> dataObjects, @Nullable PlaceholderDetail placeholderDetail) {
        this.context = context;
        this.dataObjects = dataObjects;
        if (placeholderDetail != null) {
            this.placeholderDetail = placeholderDetail;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample_2, parent, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private PlaceholderGroup placeHolderGroup;
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
            placeHolderGroup = (PlaceholderGroup) itemView.findViewById(R.id.placeHolderGroup);
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

//        holder.placeHolderGradientGroup.setPostion(position);//just for debug log

        if (placeholderDetail.isPlaceholderIsOn()) {
            //need show placeholder for 2 cards
            holder.placeHolderGroup.setAutoPlay(true);
            return;
        } else {
            holder.placeHolderGroup.setShowPlaceHolder(false);
            holder.placeHolderGroup.finishAnimation();
        }

        //set data in view
        final DataObject cardObj = dataObjects.get(position);

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

    @Override
    public int getItemCount() {
        if (placeholderDetail.isPlaceholderIsOn()) {
            // show just 2 card item in recyclerView
            return 2;
        } else {
            //normal show card item in recyclerView
            return dataObjects.size();
        }
    }


    public void addMoreDataAndPlaceholderFinish(ArrayList<DataObject> dataObjects) {

        //add new data to dataObjects
        this.dataObjects = new ArrayList<>();
        this.dataObjects.addAll(dataObjects);

        //set false show placeholder
        placeholderDetail.setPlaceholderIsOn(false);

        //update items cardView
        notifyDataSetChanged();
    }


}
