package com.linkx.spn.view.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.linkx.spn.R;
import com.linkx.spn.data.models.Step;
import com.linkx.spn.view.adapters.StepSnippetAdapter;
import java.util.List;

/**
 * Created by ulyx.yang@yeahmobi.com on 2016/11/7.
 */
public class ProjectStepViewHolder extends RuntimeViewHolder<Step> {
    @Bind(R.id.step_name)
    TextView nameText;
    @Bind(R.id.step_add_image_note)
    ImageView addImageNote;
    @Bind(R.id.step_add_video_note)
    ImageView addVideoNote;
    @Bind(R.id.step_text_note_editor)
    EditText addTextNote;
    @Bind(R.id.step_snippets)
    RecyclerView snippets;

    public ProjectStepViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bindView(List<Step> stepList, int position) {
        Step step = stepList.get(position);
        nameText.setText(step.name());
        // TODO
        addImageNote.setOnClickListener(l -> {
            Toast.makeText(itemView.getContext(), "Add Image Note", Toast.LENGTH_SHORT).show();
        });
        addVideoNote.setOnClickListener(l -> {
            Toast.makeText(itemView.getContext(), "Add Video Note", Toast.LENGTH_SHORT).show();
        });
        // TODO show text note editor
        addTextNote.setOnClickListener(l -> {
            Toast.makeText(itemView.getContext(), "Add Text Note", Toast.LENGTH_SHORT).show();
        });

        StepSnippetAdapter stepSnippetAdapter = StepSnippetAdapter.create();
        stepSnippetAdapter.addLoadingView();
        snippets.setAdapter(stepSnippetAdapter);
        // TODO load snippets from disk

    }
}
