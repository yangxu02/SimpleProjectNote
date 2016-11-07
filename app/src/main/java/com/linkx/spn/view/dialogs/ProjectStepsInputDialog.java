package com.linkx.spn.view.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.linkx.spn.R;
import com.linkx.spn.data.models.Project;
import com.linkx.spn.data.models.Status;
import com.linkx.spn.data.models.Step;
import com.linkx.spn.data.services.ProjectVisitingService;
import com.linkx.spn.data.services.LastVisitedProjectChangedEvent;
import com.linkx.spn.data.services.RxEventBus;

import java.util.Collections;
import java.util.List;

/**
 * Created by ulyx.yang on 2016/9/17.
 */
public class ProjectStepsInputDialog extends DialogFragment {
    @Bind(R.id.text_project_steps_area)
    EditText projectStepsText;
    @Bind(R.id.project_add_pre)
    ImageView pre;
    @Bind(R.id.project_add_save)
    ImageView save;

    private String projectName;
    private String projectSteps;

    public static ProjectStepsInputDialog create(String projectName, String projectSteps) {
        ProjectStepsInputDialog stepsInputDialog = new ProjectStepsInputDialog();
        Bundle bundle = new Bundle();
        bundle.putString("_project_name", projectName);
        bundle.putString("_project_steps", projectSteps);
        stepsInputDialog.setArguments(bundle);
        return stepsInputDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fdialog_add_project_steps, container);
        ButterKnife.bind(this, view);

        projectName = getArguments().getString("_project_name");
        projectSteps = getArguments().getString("_project_step");

        pre.setOnClickListener(v -> {
            projectSteps = projectStepsText.getText().toString();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            ProjectNameInputDialog.create(projectName, projectSteps).show(fm, "");
            this.dismiss();
        });
        save.setOnClickListener(v -> {
            projectSteps = projectStepsText.getText().toString();
            Project project = saveProject(projectName, parseStepsFromInput(projectSteps));
            RxEventBus.post(new LastVisitedProjectChangedEvent(project.id()));
            this.dismiss();
        });

        return view;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private List<Step> parseStepsFromInput(String projectSteps) {
        return Collections.EMPTY_LIST;
    }

    private Project saveProject(String projectName, List<Step> projectSteps) {
        // TODO
        // get project id
        ProjectVisitingService pvs = ProjectVisitingService.worker();
        long now = System.currentTimeMillis();
        String id = pvs.generateProjectId();
        Project project = Project.create(id, projectName, now, projectSteps, Status.pending);
        pvs.saveProject(project);
        return project;
    }

}
