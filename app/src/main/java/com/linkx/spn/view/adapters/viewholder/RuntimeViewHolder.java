package com.linkx.spn.view.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.List;

/**
 * Created by ulyx.yang@yeahmobi.com on 2016/11/7.
 */
public abstract class RuntimeViewHolder<T> extends RecyclerView.ViewHolder {
    public RuntimeViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindView(List<T> itemList, int position);
}
