package io.rmiri.skeleton.Master;

/**
 * Created by Rasoul Miri on 12/2/17.
 */

public class InitialAdapterSkeleton {

//    public SkeletonConfig start(final Context context, final RecyclerView recyclerView, final int layoutIdAdapter) {
//        // Initial adapter for skeleton
//        ViewTreeObserver viewTreeObserver = recyclerView.getViewTreeObserver();
//        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//
//                // Initial SkeletonDetail and set in adapter
//                SkeletonConfig skeletonConfig = new SkeletonConfig().build();
//                skeletonConfig.setRecyclerViewHeight(recyclerView.getHeight());// Height recyclerView
//
//                View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(layoutIdAdapter, null);
//                view.getRootView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//                skeletonConfig.setItemHeight(view.getRootView().getMeasuredHeight());// Height Item
//                skeletonConfig.setNumberItemShow(skeletonConfig.getRecyclerViewHeight() / skeletonConfig.getItemHeight()); // Number item skeleton in adapter
//
//                CLog.i("skeletonConfig.getItemHeight == " + skeletonConfig.getItemHeight()
//                        + "   skeletonConfig.getRecyclerViewHeight  " + skeletonConfig.getRecyclerViewHeight()
//                        + "   skeletonConfig.getNumberItemShow  " + skeletonConfig.getNumberItemShow());
//
//                // Remove ViewTreeObserver
//                ViewTreeObserver obs = recyclerView.getViewTreeObserver();
//                obs.removeGlobalOnLayoutListener(this);
//
//                return skeletonConfig;
//            }
//        });
//
//
//    }
}
