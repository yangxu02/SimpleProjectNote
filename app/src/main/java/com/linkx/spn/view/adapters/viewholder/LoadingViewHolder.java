package com.linkx.spn.view.adapters.viewholder;

import android.view.View;
import android.widget.ProgressBar;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.linkx.spn.R;
import java.util.List;

/**
 * Created by ulyx.yang@yeahmobi.com on 2016/11/7.
 */
public class LoadingViewHolder<T> extends RuntimeViewHolder<T> {
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    public LoadingViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bindView(List<T> itemList, int position) {
        this.progressBar.setIndeterminate(true);
    }
}
