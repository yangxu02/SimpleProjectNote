package com.linkx.spn.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.linkx.spn.R;
import com.linkx.spn.data.models.Step;
import com.linkx.spn.view.adapters.viewholder.ProjectStepViewHolder;
import java.util.List;

/**
 * Created by ulyx.yang@yeahmobi.com on 2016/11/1.
 */
public class ProjectStepAdapter extends BaseRecycleAdapter<Step> {
    public static ProjectStepAdapter create() {
        return new ProjectStepAdapter(new ViewHolders());
    }
    private ProjectStepAdapter(RunTimeAlbumItemViewHolders<Step> viewHolders) {
        super(viewHolders);
    }

    static class ViewHolders extends BaseRecycleAdapter.RunTimeAlbumItemViewHolders<Step> {
        @Override
        public RecyclerView.ViewHolder onCreateNonLoadingView(ViewGroup viewGroup, int viewType) {
            return new ProjectStepViewHolder(createItemView(viewGroup, R.layout.view_step_detail));
        }

        @Override
        public int deduceNonLoadingViewType(List<Step> itemList, int position) {
            return 0;
        }
    }

}
