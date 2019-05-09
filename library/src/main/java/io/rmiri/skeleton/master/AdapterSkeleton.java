package io.rmiri.skeleton.master;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.rmiri.skeleton.utils.CLog;

/**
 * Created by Rasoul Miri on 12/3/17.
 */

public abstract class AdapterSkeleton<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context context;
    protected List<T> items = Collections.emptyList();
    protected IsCanSetAdapterListener isCanSetAdapterListener;
    protected SkeletonConfig skeletonConfig = new SkeletonConfig();

    protected void measureHeightRecyclerViewAndItem(final RecyclerView recyclerView, final int idLayout) {
        ViewTreeObserver viewTreeObserver = recyclerView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                // Initial SkeletonDetail and set in adapter
                skeletonConfig.setRecyclerViewHeight(recyclerView.getHeight());// Height recyclerView

                View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(idLayout, null);
                view.getRootView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                skeletonConfig.setItemHeight(view.getRootView().getMeasuredHeight());// Height Item
                skeletonConfig.setNumberItemShow(Math.round(skeletonConfig.getRecyclerViewHeight() / skeletonConfig.getItemHeight()) + 1); // Number item skeleton in adapter

                CLog.i("skeletonConfig.getItemHeight == " + skeletonConfig.getItemHeight()
                        + "   skeletonConfig.getRecyclerViewHeight  " + skeletonConfig.getRecyclerViewHeight()
                        + "   skeletonConfig.getNumberItemShow  " + skeletonConfig.getNumberItemShow());

                // Remove ViewTreeObserver
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                isCanSetAdapterListener.isCanSet();
            }
        });
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
            CLog.i("getItemCount ==> items.size(): " + items.size());
            return items.size();
        }
    }

    public void addMoreDataAndSkeletonFinish(ArrayList<T> dataObjects) {

        // Add new data to dataObjects
        this.items = new ArrayList<>();
        this.items.addAll(dataObjects);

        // Set false show Skeleton
        skeletonConfig.setSkeletonIsOn(false);

        // If size zero
        if (this.items == null || this.items.size() == 0) {
            notifyDataSetChanged();
        }

        if (skeletonConfig.getNumberItemShow() > this.items.size()) {
            notifyItemRangeRemoved(this.items.size(), skeletonConfig.getNumberItemShow()); // remove extra items skeleton
        }

        notifyItemRangeChanged(0, this.items.size(), 1);// Update data for skeleton

    }
    //==============================================================================================
    // Getter and Setter
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public IsCanSetAdapterListener getIsCanSetAdapterListener() {
        return isCanSetAdapterListener;
    }

    public void setIsCanSetAdapterListener(IsCanSetAdapterListener IsCanSetAdapterListener) {
        this.isCanSetAdapterListener = IsCanSetAdapterListener;
    }

    public SkeletonConfig getSkeletonConfig() {
        return skeletonConfig;
    }

    public void setSkeletonConfig(SkeletonConfig skeletonConfig) {
        this.skeletonConfig = skeletonConfig;
    }
}
