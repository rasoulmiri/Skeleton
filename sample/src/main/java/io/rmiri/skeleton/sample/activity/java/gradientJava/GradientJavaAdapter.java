package io.rmiri.skeleton.sample.activity.java.gradientJava;


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

import io.rmiri.skeleton.SkeletonViewGroup;
import io.rmiri.skeleton.master.AdapterSkeleton;
import io.rmiri.skeleton.master.IsCanSetAdapterListener;
import io.rmiri.skeleton.master.SkeletonModel;
import io.rmiri.skeleton.master.SkeletonModelBuilder;
import io.rmiri.skeleton.sample.R;
import io.rmiri.skeleton.sample.data.DataObject;
import io.rmiri.skeleton.utils.ConverterUnitUtil;

import static io.rmiri.skeleton.master.SkeletonModel.SHAPE_TYPE_OVAL;
import static io.rmiri.skeleton.master.SkeletonModel.SHAPE_TYPE_RECT;


public class GradientJavaAdapter extends AdapterSkeleton<DataObject, GradientJavaAdapter.ViewHolder> {

    public GradientJavaAdapter(final Context context, final ArrayList<DataObject> items, final RecyclerView recyclerView, final IsCanSetAdapterListener isCanSetAdapterListener) {
        this.context = context;
        this.items = items;
        this.isCanSetAdapterListener = isCanSetAdapterListener;

        measureHeightRecyclerViewAndItem(recyclerView, R.layout.item_gradient_java);// Set height

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gradient_java, parent, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final SkeletonViewGroup skeletonViewGroup;
        private final CardView cardView;
        private final AppCompatImageView photoACImgV;
        private final TextView newFlagTv;
        private final TextView titleTv;
        private final TextView descriptionTv;
        private final AppCompatImageButton likeBtn;
        private final AppCompatImageButton compareImgBtn;
        private final TextView priceTv;
        private final TextView priceUnitTv;


        ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            photoACImgV = itemView.findViewById(R.id.photoACImgV);
            newFlagTv = itemView.findViewById(R.id.newFlagTv);
            titleTv = itemView.findViewById(R.id.titleTv);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
            likeBtn = itemView.findViewById(R.id.likeBtn);
            compareImgBtn = itemView.findViewById(R.id.compareImgBtn);
            priceTv = itemView.findViewById(R.id.priceTv);
            priceUnitTv = itemView.findViewById(R.id.priceUnitTv);


            cardView.setPreventCornerOverlap(false);

            skeletonViewGroup = new SkeletonViewGroup(context);

            ArrayList<SkeletonModel> skeletonModels = new ArrayList<>();

            // photoACImgV
            skeletonModels.add(new SkeletonModelBuilder()
                    .setChildView(photoACImgV)
                    .build());

            // titleTv
            skeletonModels.add(new SkeletonModelBuilder()
                    .setChildView(titleTv)
                    .setCornerRadius(ConverterUnitUtil.dpToPx(getContext(), 5))
                    .setPaddingBottom(ConverterUnitUtil.dpToPx(getContext(), 2))
                    .build());

            // titleTv
            skeletonModels.add(new SkeletonModelBuilder()
                    .setChildView(descriptionTv)
                    .setCornerRadius(ConverterUnitUtil.dpToPx(getContext(), 5))
                    .setPaddingTop(ConverterUnitUtil.dpToPx(getContext(), 2))
                    .build());

            // compareImgBtn
            skeletonModels.add(new SkeletonModelBuilder()
                    .setChildView(compareImgBtn)
                    .setShapeType(SHAPE_TYPE_OVAL)
                    .setPadding(ConverterUnitUtil.dpToPx(getContext(), 4f))
                    .build());

            // likeBtn
            skeletonModels.add(new SkeletonModelBuilder()
                    .setChildView(likeBtn)
                    .setShapeType(SHAPE_TYPE_OVAL)
                    .setPadding(ConverterUnitUtil.dpToPx(getContext(), 4f))
                    .build());

            // priceTv and priceUnitTv
            skeletonModels.add(new SkeletonModelBuilder()
                    .setStartView(priceUnitTv)
                    .setEndView(priceTv)
                    .setShapeType(SHAPE_TYPE_RECT)
                    .setCornerRadius(ConverterUnitUtil.dpToPx(getContext(), 5))
                    .build());

            skeletonViewGroup.setSkeletonModels(skeletonModels);

            SkeletonModel skeletonAttribute = new SkeletonModel();
            skeletonAttribute.setAutoStartAnimation(true);

            skeletonViewGroup.setSkeletonModel(skeletonAttribute);

            // Add SkeletonGroup
            ViewGroup.LayoutParams layout = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            cardView.addView(skeletonViewGroup, layout);

        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (skeletonConfig.isSkeletonIsOn()) {
            holder.skeletonViewGroup.startAnimation();
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
