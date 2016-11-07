package com.linkx.spn.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.linkx.spn.R;
import com.linkx.spn.data.models.Model;
import com.linkx.spn.data.models.TextNote;
import com.linkx.spn.view.adapters.viewholder.StepSnippetViewHolder;
import com.linkx.spn.view.adapters.viewholder.TextNoteViewHolder;
import java.util.List;

/**
 * Created by ulyx.yang@yeahmobi.com on 2016/11/1.
 */
public class TextNoteAdapter extends BaseRecycleAdapter<TextNote> {
    public static TextNoteAdapter create() {
        return new TextNoteAdapter(new ViewHolders());
    }
    private TextNoteAdapter(RunTimeAlbumItemViewHolders<TextNote> viewHolders) {
        super(viewHolders);
    }

    static class ViewHolders extends RunTimeAlbumItemViewHolders<TextNote> {
        @Override
        public RecyclerView.ViewHolder onCreateNonLoadingView(ViewGroup viewGroup, int viewType) {
            return new TextNoteViewHolder(createItemView(viewGroup, R.layout.item_text_note));
        }

        @Override
        public int deduceNonLoadingViewType(List<TextNote> itemList, int position) {
            return 0;
        }
    }

}
