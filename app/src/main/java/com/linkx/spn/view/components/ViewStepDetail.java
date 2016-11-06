package com.linkx.spn.view.components;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.linkx.spn.R;

public class ViewStepDetail extends FrameLayout {

    @Bind(R.id.step_name)
    TextView stepName;
    @Bind(R.id.step_add_video_note)
    ImageView addVideoNote;
    @Bind(R.id.step_add_image_note)
    ImageView addImageNote;
    @Bind(R.id.step_text_note_editor)
    EditText textNoteEditor;
    @Bind(R.id.step_snippets)
    RecyclerView snippets;

    public ViewStepDetail(Context context) {
        super(context);
        setup(null);
    }

    public ViewStepDetail(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(attrs);
    }

    public ViewStepDetail(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(attrs);
    }

    private void setup(AttributeSet attrs) {
        inflate(getContext(), R.layout.view_step_detail, this);
        ButterKnife.bind(this);
    }

}
