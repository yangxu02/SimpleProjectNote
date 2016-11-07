package com.linkx.spn.view.adapters.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.linkx.spn.R;
import com.linkx.spn.data.models.MediaNote;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by ulyx.yang@yeahmobi.com on 2016/11/7.
 */
public class VideoNoteViewHolder extends RuntimeViewHolder<MediaNote> {
    @Bind(R.id.video_note_icon)
    ImageView noteImage;
    @Bind(R.id.video_note_text)
    TextView noteText;

    public VideoNoteViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bindView(List<MediaNote> mediaNoteList, int position) {
        MediaNote mediaNote = mediaNoteList.get(position);
        noteText.setText(mediaNote.basic().createdTime() + "");
    }
}
