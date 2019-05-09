package io.rmiri.skeleton.sample.adapter.xml;


import android.content.Context;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.rmiri.skeleton.SkeletonViewGroup;
import io.rmiri.skeleton.master.AdapterSkeleton;
import io.rmiri.skeleton.master.IsCanSetAdapterListener;
import io.rmiri.skeleton.sample.R;
import io.rmiri.skeleton.sample.data.DataObject;


public class AdapterFadeXml1 extends AdapterSkeleton<DataObject, AdapterFadeXml1.ViewHolder> {


    public AdapterFadeXml1(final Context context, final ArrayList<DataObject> items, final RecyclerView recyclerView, final IsCanSetAdapterListener IsCanSetAdapterListener) {
        this.context = context;
        this.items = items;
        this.isCanSetAdapterListener = IsCanSetAdapterListener;

        measureHeightRecyclerViewAndItem(recyclerView, R.layout.item_fade_xml_1);// Set height

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fade_xml_1, parent, false);
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

        holder.skeletonViewGroup.setPosition(position);//just for debug log

        if (skeletonConfig.isSkeletonIsOn()) {
            // Need show skeletonGroup for 2 cards
            holder.skeletonViewGroup.setAutoPlay(true);
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
