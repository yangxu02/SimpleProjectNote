package com.linkx.spn.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
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
import com.linkx.spn.view.dialogs.ProjectAddDialog;
import rx.Subscriber;
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

        ProjectVisitingService service = ProjectVisitingService.worker();
        service.getProjectToShow(new Subscriber<Project>() {
            Project project;
            @Override
            public void onCompleted() {
                if (null == project) {
                    showProjectAddDialog();
                } else {
                    projectDetail.update(project);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.w("", e);
            }

            @Override
            public void onNext(Project project) {
                this.project = project;
            }
        });

        subscription = RxEventBus.onEventMainThread(LastVisitedProjectChangedEvent.class, e -> {
            service.getProjectById(e.projectId, new Subscriber<Project>() {
                Project project;
                @Override
                public void onCompleted() {
                    projectDetail.update(project);
                    projectListPanel.setVisibility(View.GONE);

                }

                @Override
                public void onError(Throwable e) {
                    Log.w("", e);
                    projectListPanel.setVisibility(View.GONE);
                }

                @Override
                public void onNext(Project project) {
                    this.project = project;
                }
            });
        });
    }

    private void setupActionMenu() {
        actionMenu.getLeftPanel().setOnClickListener(l -> {
            projectListPanel.update();
            projectListPanel.setVisibility(View.VISIBLE);
        });

        actionMenu.getAddProject().setOnClickListener(l -> showProjectAddDialog());
    }

    private void showProjectAddDialog() {
        FragmentManager fm = this.getSupportFragmentManager();
        ProjectAddDialog.create("", "").show(fm, "");
    }

}
