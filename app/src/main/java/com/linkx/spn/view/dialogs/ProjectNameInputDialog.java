package com.linkx.spn.view.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.common.base.Strings;
import com.linkx.spn.R;

import java.util.UUID;

/**
 * Created by ulyx.yang on 2016/9/17.
 */
public class ProjectNameInputDialog extends DialogFragment {
    @Bind(R.id.text_project_name_area)
    EditText projectNameText;
    @Bind(R.id.project_add_pre)
    ImageView pre;
    @Bind(R.id.project_add_next)
    ImageView next;

    String projectSteps;
    String projectName;

    public static ProjectNameInputDialog create(String projectName, String projectSteps) {
        ProjectNameInputDialog nameInputDialog = new ProjectNameInputDialog();
        Bundle bundle = new Bundle();
        bundle.putString("_project_name", projectName);
        bundle.putString("_project_steps", projectSteps);
        nameInputDialog.setArguments(bundle);
        return nameInputDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fdialog_add_project_name, container);
        ButterKnife.bind(this, view);
        projectName = getArguments().getString("_project_name");
        if (!Strings.isNullOrEmpty(projectName)) {
            projectNameText.setText(projectName);
        }
        projectSteps = getArguments().getString("_project_step");

        pre.setOnClickListener(v -> this.dismiss());
        next.setOnClickListener(v -> {
            projectName = projectNameText.getText().toString();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            ProjectStepsInputDialog.create(projectName, projectSteps);
        });

        return view;
    }
}
