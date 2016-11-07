package com.linkx.spn.view.components;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.linkx.spn.R;
import com.linkx.spn.data.models.Project;
import com.linkx.spn.data.services.RxEventBus;
import com.linkx.spn.data.services.LastVisitedProjectChangedEvent;

import java.util.Collections;
import java.util.List;

public class ViewProjectListPanel extends FrameLayout {

    @Bind(R.id.project_list_items)
    RecyclerView projectListItems;

    public ViewProjectListPanel(Context context) {
        super(context);
        setup(null);
    }

    public ViewProjectListPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(attrs);
    }

    public ViewProjectListPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(attrs);
    }

    private void setup(AttributeSet attrs) {
        inflate(getContext(), R.layout.view_project_list_panel, this);
        ButterKnife.bind(this);
    }

    public void update() {
        projectListItems.setAdapter(new RecyclerView.Adapter() {
            List<Project> projectList = getProjectList();
            Project lastSelected = getLastSelectedProject();
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_list, parent, false);
                return new TextViewHolder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                TextViewHolder viewHolder = (TextViewHolder) holder;
                Project project = projectList.get(position);
                viewHolder.textView.setText(project.name());
                if (project.id().equals(lastSelected.id())) {
                    viewHolder.textView.getPaint().setFakeBoldText(true);
                }

                holder.itemView.setOnClickListener(l -> {
                    // TODO update detail view
                    RxEventBus.post(new LastVisitedProjectChangedEvent(project.id()));
                });
            }

            @Override
            public int getItemCount() {
                return projectList.size();
            }

            class TextViewHolder extends RecyclerView.ViewHolder {
                TextView textView;
                TextViewHolder(View itemView) {
                    super(itemView);
                    ButterKnife.bind(this, itemView);
                }
            }
        });
    }

    List<Project> getProjectList() {
        // TODO
        return Collections.EMPTY_LIST;
    }

    Project getLastSelectedProject() {
        // TODO
        return null;
    }
}
