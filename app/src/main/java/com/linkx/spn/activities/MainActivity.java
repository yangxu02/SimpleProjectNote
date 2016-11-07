package com.linkx.spn.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.linkx.spn.R;
import com.linkx.spn.data.models.Project;
import com.linkx.spn.data.services.ProjectVisitingService;
import com.linkx.spn.data.services.RxEventBus;
import com.linkx.spn.data.services.LastVisitedProjectChangedEvent;
import com.linkx.spn.view.components.ViewActionMenu;
import com.linkx.spn.view.components.ViewProjectDetail;
import com.linkx.spn.view.components.ViewProjectListPanel;
import com.linkx.spn.view.dialogs.ProjectNameInputDialog;
import rx.Subscription;

public class MainActivity extends BaseActivity {

    @Bind(R.id.project_list_panel)
    ViewProjectListPanel projectListPanel;
    @Bind(R.id.action_menu)
    ViewActionMenu actionMenu;
    @Bind(R.id.project_detail)
    ViewProjectDetail projectDetail;

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupViews();
    }

    @Override
    protected void onDestroy() {
        subscription.unsubscribe();
        super.onDestroy();
    }

    private void setupViews() {
        setupActionMenu();

        Project lastSelected = getLastSelectedProject();
        if (null == lastSelected) {
            showProjectAddDialog();
        } else {
            projectDetail.update(lastSelected);
        }

        subscription = RxEventBus.onEventMainThread(LastVisitedProjectChangedEvent.class, visitingProjectChangedEvent -> {
            Project project = ProjectVisitingService.worker().getProjectById(visitingProjectChangedEvent.projectId);
            projectDetail.update(project);
            projectListPanel.setVisibility(View.GONE);
        });
    }

    private void setupActionMenu() {
        actionMenu.getLeftPanel().setOnClickListener(l -> {
            projectListPanel.update();
            projectListPanel.setVisibility(View.VISIBLE);
        });

        actionMenu.getAddProject().setOnClickListener(l -> showProjectAddDialog());
    }

    private Project getLastSelectedProject() {
        return null;
    }

    private void showProjectAddDialog() {
        FragmentManager fm = this.getSupportFragmentManager();
        ProjectNameInputDialog.create("", "").show(fm, "");
    }

}
