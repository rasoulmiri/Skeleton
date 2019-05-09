package io.rmiri.skeleton.sample.activity.xml.gradientXml;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.rmiri.skeleton.SkeletonViewGroup;
import io.rmiri.skeleton.master.AdapterSkeleton;
import io.rmiri.skeleton.master.IsCanSetAdapterListener;
import io.rmiri.skeleton.sample.R;
import io.rmiri.skeleton.sample.data.DataObject;


public class GradientXmlAdapter extends AdapterSkeleton<DataObject, GradientXmlAdapter.ViewHolder> {


    public GradientXmlAdapter(final Context context, final ArrayList<DataObject> items, final RecyclerView recyclerView, final IsCanSetAdapterListener isCanSetAdapterListener) {
        this.context = context;
        this.items = items;
        this.isCanSetAdapterListener = isCanSetAdapterListener;

        measureHeightRecyclerViewAndItem(recyclerView, R.layout.item_gradient_xml);// Set height

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gradient_xml, parent, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        private final SkeletonViewGroup skeletonViewGroup;
        private final AppCompatImageView photoACImgV;
        private final TextView newFlagTv;
        private final TextView titleTv;
        private final TextView descriptionTv;
        private final AppCompatImageButton likeBtn;
        private final AppCompatImageButton compareImgBtn;
        private final TextView priceTv;


        ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            skeletonViewGroup = itemView.findViewById(R.id.skeletonGroup);
            photoACImgV = itemView.findViewById(R.id.photoACImgV);
            newFlagTv = itemView.findViewById(R.id.newFlagTv);
            titleTv = itemView.findViewById(R.id.titleTv);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
            likeBtn = itemView.findViewById(R.id.likeBtn);
            compareImgBtn = itemView.findViewById(R.id.compareImgBtn);
            priceTv = itemView.findViewById(R.id.priceTv);

        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.cardView.setPreventCornerOverlap(false);

//        holder.skeletonGroup.setPosition(position);//just for debug log

        if (skeletonConfig.isSkeletonIsOn()) {
            return;
        } else {
            holder.skeletonViewGroup.setShowSkeleton(false);
            holder.skeletonViewGroup.finishAnimation();
        }

        // Set data in view
        final DataObject cardObj = items.get(position);

        holder.titleTv.setText(cardObj.getTitle());
        holder.descriptionTv.setText(cardObj.getDescription());
        holder.priceTv.setText(cardObj.getPrice());

        if (cardObj.isNew()) {
            holder.newFlagTv.setVisibility(View.VISIBLE);
        } else {
            holder.newFlagTv.setVisibility(View.GONE);
        }

        // Set photo by Picasso lib
        Picasso.with(context).load(cardObj.getPhoto()).into(holder.photoACImgV);

    }



}
