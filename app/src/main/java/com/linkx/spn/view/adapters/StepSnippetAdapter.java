package com.linkx.spn.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.linkx.spn.R;
import com.linkx.spn.data.models.Step;
import com.linkx.spn.view.adapters.viewholder.ProjectStepViewHolder;
import com.linkx.spn.view.adapters.viewholder.StepSnippetViewHolder;
import java.util.List;

/**
 * Created by ulyx.yang@yeahmobi.com on 2016/11/1.
 */
public class StepSnippetAdapter extends BaseRecycleAdapter<Step> {
    public static StepSnippetAdapter create() {
        return new StepSnippetAdapter(new ViewHolders());
    }
    private StepSnippetAdapter(RunTimeAlbumItemViewHolders<Step> viewHolders) {
        super(viewHolders);
    }

    static class ViewHolders extends RunTimeAlbumItemViewHolders<Step> {
        @Override
        public RecyclerView.ViewHolder onCreateNonLoadingView(ViewGroup viewGroup, int viewType) {
            return new StepSnippetViewHolder(createItemView(viewGroup, R.layout.view_snippet_detail));
        }

        @Override
        public int deduceNonLoadingViewType(List<Step> itemList, int position) {
            return 0;
        }
    }

}
