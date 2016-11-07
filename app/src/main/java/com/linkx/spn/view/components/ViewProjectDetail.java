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
import com.linkx.spn.data.models.Project;
import com.linkx.spn.view.adapters.ProjectStepAdapter;

public class ViewProjectDetail extends FrameLayout {

    @Bind(R.id.project_name)
    TextView projectName;
    @Bind(R.id.project_steps)
    RecyclerView projectSteps;

    public ViewProjectDetail(Context context) {
        super(context);
        setup(null);
    }

    public ViewProjectDetail(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(attrs);
    }

    public ViewProjectDetail(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(attrs);
    }

    private void setup(AttributeSet attrs) {
        inflate(getContext(), R.layout.view_project_detail, this);
        ButterKnife.bind(this);
    }

    public void update(Project project) {
        projectName.setText(project.name());
        ProjectStepAdapter projectStepAdapter = ProjectStepAdapter.create();
        projectSteps.setAdapter(projectStepAdapter);
    }

}
