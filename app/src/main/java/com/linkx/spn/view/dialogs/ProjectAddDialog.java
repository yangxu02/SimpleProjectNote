package com.linkx.spn.view.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.linkx.spn.R;
import com.linkx.spn.data.models.Project;
import com.linkx.spn.data.models.Status;
import com.linkx.spn.data.models.Step;
import com.linkx.spn.data.services.LastVisitedProjectChangedEvent;
import com.linkx.spn.data.services.ProjectVisitingService;
import com.linkx.spn.data.services.RxEventBus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rx.Subscriber;

/**
 * Created by ulyx.yang on 2016/9/17.
 */
public class ProjectAddDialog extends DialogFragment {
    @Bind(R.id.text_project_name_area)
    EditText projectNameText;
    @Bind(R.id.text_project_steps_area)
    EditText projectStepText;
    @Bind(R.id.project_add_cancel)
    ImageView cancel;
    @Bind(R.id.project_add_save)
    ImageView save;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    String projectSteps;
    String projectName;

    public static ProjectAddDialog create(String projectName, String projectSteps) {
        ProjectAddDialog nameInputDialog = new ProjectAddDialog();
        Bundle bundle = new Bundle();
        bundle.putString("_project_name", projectName);
        bundle.putString("_project_steps", projectSteps);
        nameInputDialog.setArguments(bundle);
        return nameInputDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fdialog_add_project, container);
        ButterKnife.bind(this, view);
        projectName = getArguments().getString("_project_name");
        if (!Strings.isNullOrEmpty(projectName)) {
            projectNameText.setText(projectName);
        }
        projectSteps = getArguments().getString("_project_step");
        if (!Strings.isNullOrEmpty(projectSteps)) {
            projectStepText.setText(projectSteps);
        }

        cancel.setOnClickListener(v -> this.dismiss());
        save.setOnClickListener(v -> {
            projectName = projectNameText.getText().toString();
            projectSteps = projectStepText.getText().toString();
            saveProject(projectName, projectSteps);
        });

        return view;
    }

    private void saveProject(String projectName, String stepStr) {
        DialogFragment dialog = this;
        ProjectVisitingService pvs = ProjectVisitingService.worker();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
        pvs.saveProject(projectName, stepStr, new Subscriber<Project>() {
            Project project;
            @Override
            public void onCompleted() {
                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.GONE);
                RxEventBus.post(new LastVisitedProjectChangedEvent(project.id()));
                dialog.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "save project with name=" + projectName + "failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Project project) {
                this.project = project;
            }
        });
    }

}
