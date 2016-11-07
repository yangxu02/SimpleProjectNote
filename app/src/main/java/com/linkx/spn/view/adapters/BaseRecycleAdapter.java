package com.linkx.spn.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.linkx.spn.R;
import com.linkx.spn.utils.DisplayUtils;
import com.linkx.spn.view.adapters.viewholder.LoadingViewHolder;
import com.linkx.spn.view.adapters.viewholder.RuntimeViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ulyx.yang@yeahmobi.com on 2016/11/1.
 */
public class BaseRecycleAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> itemList = new ArrayList<>();
    // animation
    private int lastPosition = -1;
    final private RunTimeAlbumItemViewHolders<T> viewHolders;

    public BaseRecycleAdapter(RunTimeAlbumItemViewHolders<T> viewHolders) {
        this.viewHolders = viewHolders;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return viewHolders.onCreate(viewGroup, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RuntimeViewHolder<T> runtimeViewHolder = (RuntimeViewHolder<T>) viewHolder;
        runtimeViewHolder.bindView(itemList, position);
        // add animation
        Animation animation = AnimationUtils.loadAnimation(viewHolder.itemView.getContext(),
                (position > lastPosition) ? R.anim.up_from_bottom
                    : R.anim.down_from_top);
        viewHolder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    public BaseRecycleAdapter add(T item) {
         if (!this.itemList.isEmpty() && null == getLast()) {
            this.removeLast();
        }
        this.itemList.add(item);
        notifyItemInserted(itemList.size() - 1);
        return this;
    }

    public BaseRecycleAdapter addLoadingView() {
        this.itemList.add(null);
        notifyItemInserted(itemList.size() - 1);
        return this;
    }

    public BaseRecycleAdapter addAll(List<T> items) {
        if (!this.itemList.isEmpty() && null == getLast()) {
            this.removeLast();
        }
        this.itemList.addAll(items);
        notifyDataSetChanged();
        return this;
    }

    public BaseRecycleAdapter removeLast() {
        int index = itemList.size() - 1;
        this.itemList.remove(index);
        notifyItemRemoved(itemList.size());
        return this;
    }

    public T getLast() {
        int index = itemList.size() - 1;
        return this.itemList.get(index);
    }

    public static abstract class RunTimeAlbumItemViewHolders<T> {
        final int VIEW_TYPE_LOADING = Integer.MIN_VALUE;

        View createItemView(ViewGroup viewGroup, int layoutId) {
            Context context = viewGroup.getContext();
            View view = LayoutInflater.from(context).inflate(layoutId, viewGroup, false);
            int width = DisplayUtils.getWidthPixels(context);
            int height = view.getLayoutParams().height;
            view.setLayoutParams(new RecyclerView.LayoutParams(width, height));
            return view;
        }


        public abstract RecyclerView.ViewHolder onCreateNonLoadingView(ViewGroup viewGroup, int viewType);

        public RecyclerView.ViewHolder onCreate(ViewGroup viewGroup, int viewType) {
            if (viewType == VIEW_TYPE_LOADING) {
                return new LoadingViewHolder(createItemView(viewGroup, R.layout.list_item_loading));
            } else {
                return onCreateNonLoadingView(viewGroup, viewType);
            }
        }

        public abstract int deduceNonLoadingViewType(List<T> itemList, int position);

        public int deduceViewType(List<T> itemList, int position) {
            T item = itemList.get(position);
            if (null == item) return VIEW_TYPE_LOADING;
            return deduceNonLoadingViewType(itemList, position);
        }

    }


}

