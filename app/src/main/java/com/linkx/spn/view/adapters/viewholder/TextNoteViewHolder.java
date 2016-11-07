package com.linkx.spn.view.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.linkx.spn.R;
import com.linkx.spn.data.models.NoteSnippet;
import com.linkx.spn.data.models.TextNote;
import com.linkx.spn.view.adapters.TextNoteAdapter;
import java.util.List;

/**
 * Created by ulyx.yang@yeahmobi.com on 2016/11/7.
 */
public class TextNoteViewHolder extends RuntimeViewHolder<TextNote> {
    @Bind(R.id.text_note_header)
    TextView noteHeaderText;
    @Bind(R.id.text_note_text)
    TextView noteText;

    public TextNoteViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bindView(List<TextNote> textNoteList, int position) {
        TextNote textNote = textNoteList.get(position);
        noteHeaderText.setText(textNote.basic().createdTime() + "");
        noteText.setText(textNote.text());
    }
}
