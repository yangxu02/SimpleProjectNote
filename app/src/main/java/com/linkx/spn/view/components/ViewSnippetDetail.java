package com.linkx.spn.view.components;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.linkx.spn.R;

public class ViewSnippetDetail extends FrameLayout {

    @Bind(R.id.snippet_day)
    TextView day;
    @Bind(R.id.snippet_video_note_list)
    RecyclerView videoNoteList;
    @Bind(R.id.snippet_image_note_list)
    RecyclerView imageNoteList;
    @Bind(R.id.snippet_text_note_list)
    RecyclerView textNoteList;

    public ViewSnippetDetail(Context context) {
        super(context);
        setup(null);
    }

    public ViewSnippetDetail(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(attrs);
    }

    public ViewSnippetDetail(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(attrs);
    }

    private void setup(AttributeSet attrs) {
        inflate(getContext(), R.layout.view_snippet_detail, this);
        ButterKnife.bind(this);
    }

}
