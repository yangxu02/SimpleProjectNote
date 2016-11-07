package com.linkx.spn.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.linkx.spn.R;
import com.linkx.spn.data.models.MediaNote;
import com.linkx.spn.view.adapters.viewholder.ImageNoteViewHolder;
import com.linkx.spn.view.adapters.viewholder.VideoNoteViewHolder;
import java.util.List;

/**
 * Created by ulyx.yang@yeahmobi.com on 2016/11/1.
 */
public class MediaNoteAdapter extends BaseRecycleAdapter<MediaNote> {
    public static MediaNoteAdapter create() {
        return new MediaNoteAdapter(new ViewHolders());
    }
    private MediaNoteAdapter(RunTimeAlbumItemViewHolders<MediaNote> viewHolders) {
        super(viewHolders);
    }

    static class ViewHolders extends RunTimeAlbumItemViewHolders<MediaNote> {
        final int VIEW_TYPE_IMAGE_NOTE = -1;
        final int VIEW_TYPE_VIDEO_NOTE = -2;

        @Override
        public RecyclerView.ViewHolder onCreateNonLoadingView(ViewGroup viewGroup, int viewType) {
            if (VIEW_TYPE_IMAGE_NOTE == viewType) {
                return new ImageNoteViewHolder(createItemView(viewGroup, R.layout.item_image_note));
            } else if (VIEW_TYPE_VIDEO_NOTE == viewType) {
                return new VideoNoteViewHolder(createItemView(viewGroup, R.layout.item_video_note));
            }
            return null;
        }

        @Override
        public int deduceNonLoadingViewType(List<MediaNote> itemList, int position) {
            MediaNote mediaNote = itemList.get(position);
            if (MediaNote.Type.image.equals(mediaNote.type())) {
                return VIEW_TYPE_IMAGE_NOTE;
            } else if (MediaNote.Type.video.equals(mediaNote.type())) {
                return VIEW_TYPE_VIDEO_NOTE;
            }
            return 0;
        }
    }

}
