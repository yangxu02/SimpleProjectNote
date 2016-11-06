package com.linkx.spn.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.linkx.spn.R;
import com.linkx.spn.data.models.Project;
import com.linkx.spn.data.services.NGImageService;
import com.linkx.spn.data.services.RxEventBus;
import com.linkx.spn.data.services.VisitingProjectChangedEvent;
import com.linkx.spn.view.adapters.AlbumItemAdapter;
import com.linkx.spn.view.components.ViewActionMenu;
import com.linkx.spn.view.components.ViewProjectDetail;
import com.linkx.spn.view.components.ViewProjectListPanel;
import com.linkx.spn.view.dialogs.ProjectNameInputDialog;
import com.linkx.spn.view.listeners.EndlessRecyclerOnScrollListener;
import rx.Subscription;
import rx.functions.Action1;

import java.util.Collections;
import java.util.List;

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

        subscription = RxEventBus.onEventMainThread(VisitingProjectChangedEvent.class, visitingProjectChangedEvent -> {
            Project project = visitingProjectChangedEvent.getProject();
            projectDetail.update(project);
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
