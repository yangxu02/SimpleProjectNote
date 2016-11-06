package com.linkx.spn.view.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.linkx.spn.R;
import com.linkx.spn.activities.AlbumItemActivity;
import com.linkx.spn.data.models.AlbumItem;
import com.linkx.spn.data.services.NGImageDownloader;
import com.linkx.spn.utils.DisplayUtils;
import com.linkx.spn.view.Transition;
import com.linkx.spn.view.components.TextDrawable;
import com.linkx.spn.view.components.timeline.TimelineView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ulyx.yang@yeahmobi.com on 2016/11/1.
 */
public class AlbumItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AlbumItem> itemList = new ArrayList<>();

    public enum AdapterType {
        ALBUM_ALL,
        ALBUM_HISTORY,
    }

    private AdapterType adapterType = AdapterType.ALBUM_ALL;
    private int lastPosition = -1;

    public AlbumItemAdapter() {
    }

    public AlbumItemAdapter(AdapterType adapterType) {
        this.adapterType = adapterType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return RunTimeAlbumItemViewHolders.onCreate(viewGroup, viewType, adapterType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RuntimeViewHolder<AlbumItem> runtimeViewHolder = (RuntimeViewHolder<AlbumItem>) viewHolder;
        runtimeViewHolder.bindView(itemList, position);
        // add animation
        Animation animation;
        if (AdapterType.ALBUM_HISTORY.equals(adapterType)) {
            animation = AnimationUtils.loadAnimation(viewHolder.itemView.getContext(),
                (position > lastPosition) ? R.anim.right_to_left
                    : R.anim.left_from_right);
        } else {
            animation = AnimationUtils.loadAnimation(viewHolder.itemView.getContext(),
                (position > lastPosition) ? R.anim.up_from_bottom
                    : R.anim.down_from_top);
        }
        viewHolder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    @Override
    public int getItemViewType(int position) {
        return RunTimeAlbumItemViewHolders.deduceViewType(itemList, position, adapterType);
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

    public AlbumItemAdapter setAdapterType(AdapterType adapterType) {
        this.adapterType = adapterType;
        return this;
    }

    public AlbumItemAdapter add(AlbumItem item) {
         if (!this.itemList.isEmpty() && null == getLast()) {
            this.removeLast();
        }
        this.itemList.add(item);
        notifyItemInserted(itemList.size() - 1);
        return this;
    }

    public AlbumItemAdapter addLoadingView() {
        this.itemList.add(null);
        notifyItemInserted(itemList.size() - 1);
        return this;
    }

    public AlbumItemAdapter addAll(List<AlbumItem> items) {
        if (!this.itemList.isEmpty() && null == getLast()) {
            this.removeLast();
        }
        this.itemList.addAll(items);
        notifyDataSetChanged();
        return this;
    }

    public AlbumItemAdapter removeLast() {
        int index = itemList.size() - 1;
        this.itemList.remove(index);
        notifyItemRemoved(itemList.size());
        return this;
    }

    public AlbumItem getLast() {
        int index = itemList.size() - 1;
        return this.itemList.get(index);
    }

    public static abstract class RuntimeViewHolder<T> extends RecyclerView.ViewHolder {
        public RuntimeViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bindView(List<T> itemList, int position);
    }

    public static final class RunTimeAlbumItemViewHolders {

        final static int VIEW_TYPE_LOADING = -1;
        final static int VIEW_TYPE_ALBUM = -2;

        public static RecyclerView.ViewHolder onCreate(ViewGroup viewGroup, int viewType, AdapterType adapterType) {
            int width = DisplayUtils.getWidthPixels(viewGroup.getContext());
            if (VIEW_TYPE_LOADING == viewType) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_album_loading, viewGroup, false);
                int height = view.getLayoutParams().height;
                view.setLayoutParams(new RecyclerView.LayoutParams(width, height));
                return new LoadingViewHolder(view);
            }

            if (AdapterType.ALBUM_HISTORY.equals(adapterType)) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_history_item_album, viewGroup, false);
                int height = view.getLayoutParams().height;
                view.setLayoutParams(new RecyclerView.LayoutParams(width, height));
                return new HistoryAlbumItemViewHolder(view);
            } else {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_album, viewGroup, false);
                int height = view.getLayoutParams().height;
                view.setLayoutParams(new RecyclerView.LayoutParams(width, height));
                return new AlbumItemViewHolder(view, viewType);
            }
        }

        public static int deduceViewType(List<AlbumItem> itemList, int position,
                                         AdapterType adapterType) {
            AlbumItem item = itemList.get(position);
            if (null == item) return VIEW_TYPE_LOADING;
            if (AdapterType.ALBUM_HISTORY.equals(adapterType)) return VIEW_TYPE_ALBUM;
            return TimelineView.getTimeLineViewType(position, itemList.size());
        }

    }

    public static class AlbumItemViewHolder extends RuntimeViewHolder<AlbumItem> {
        @Bind(R.id.time_marker)
        TimelineView timelineView;
        @Bind(R.id.album_item_time)
        ImageView timeTextView;
        @Bind(R.id.album_item_thumb)
        ImageView thumbView;
        @Bind(R.id.album_item_title)
        TextView title;
        @Bind(R.id.album_item_desc)
        TextView desc;
        public AlbumItemViewHolder(View view, int viewType) {
            super(view);
            ButterKnife.bind(this, view);
            timelineView.initLine(viewType);
        }

        @Override
        public void bindView(List<AlbumItem> itemList, int position) {
            AlbumItem item = itemList.get(position);
            NGImageDownloader.with(this.itemView.getContext())
//            Picasso.with(this.itemView.getContext())
                .load(item.thumb())
                .into(this.thumbView);

            TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .textColor(0xff009688)
                .withBorder(1)
                .useFont(Typeface.MONOSPACE)
                .fontSize(30) /* size in px */
                .bold()
                .endConfig()
                .buildRect(item.inputTime(), Color.TRANSPARENT);
            this.timeTextView.setImageDrawable(drawable);
            this.title.setText(item.title());
            this.desc.setText(item.description());

            this.thumbView.setOnClickListener(l -> {
                Activity activity = (Activity) this.itemView.getContext();
                AlbumItemActivity.launch(activity, item, Transition.PUSH_UP);
            });
        }
    }

    public static class HistoryAlbumItemViewHolder extends RuntimeViewHolder<AlbumItem> {
        @Bind(R.id.album_item_thumb)
        ImageView thumbView;
        @Bind(R.id.album_item_title)
        TextView title;
        @Bind(R.id.album_item_desc)
        TextView desc;
        public HistoryAlbumItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void bindView(List<AlbumItem> itemList, int position) {
            AlbumItem item = itemList.get(position);
//            Picasso.with(this.itemView.getContext())
            NGImageDownloader.with(this.itemView.getContext())
                .load(item.thumb())
                .into(this.thumbView);
            this.title.setText(item.title());
            this.desc.setText(item.description());

            this.thumbView.setOnClickListener(l -> {
                Activity activity = (Activity) this.itemView.getContext();
                AlbumItemActivity.launch(activity, item, Transition.PUSH_UP);
            });
        }
    }

    public static class LoadingViewHolder extends RuntimeViewHolder<AlbumItem> {
        @Bind(R.id.progress_bar)
        ProgressBar progressBar;
        public LoadingViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void bindView(List<AlbumItem> itemList, int position) {
            this.progressBar.setIndeterminate(true);
        }
    }
}

