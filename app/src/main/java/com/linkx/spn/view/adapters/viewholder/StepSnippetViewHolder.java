package com.linkx.spn.view.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.linkx.spn.R;
import com.linkx.spn.data.models.NoteSnippet;
import com.linkx.spn.view.adapters.MediaNoteAdapter;
import com.linkx.spn.view.adapters.TextNoteAdapter;
import java.util.List;

/**
 * Created by ulyx.yang@yeahmobi.com on 2016/11/7.
 */
public class StepSnippetViewHolder extends RuntimeViewHolder<NoteSnippet> {
    @Bind(R.id.snippet_day)
    TextView dayText;
    @Bind(R.id.snippet_image_note_list)
    RecyclerView imageNotes;
    @Bind(R.id.snippet_video_note_list)
    RecyclerView videoNotes;
    @Bind(R.id.snippet_text_note_list)
    RecyclerView textNotes;

    public StepSnippetViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bindView(List<NoteSnippet> snippetList, int position) {
        NoteSnippet snippet = snippetList.get(position);
        dayText.setText(snippet.day());
        MediaNoteAdapter imageNotesAdapter = MediaNoteAdapter.create();
        imageNotesAdapter.addLoadingView();
        imageNotes.setAdapter(imageNotesAdapter);
        imageNotesAdapter.addAll(snippet.imageNodeList());

        MediaNoteAdapter videoNotesAdapter = MediaNoteAdapter.create();
        videoNotesAdapter.addLoadingView();
        videoNotes.setAdapter(videoNotesAdapter);
        videoNotesAdapter.addAll(snippet.videoNodeList());

        TextNoteAdapter textNotesAdapter = TextNoteAdapter.create();
        textNotesAdapter.addLoadingView();
        textNotes.setAdapter(textNotesAdapter);
        textNotesAdapter.addAll(snippet.textNodeList());

    }
}
