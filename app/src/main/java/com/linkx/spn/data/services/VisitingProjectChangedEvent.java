package com.linkx.spn.data.services;

import com.linkx.spn.data.models.Project;

/**
 * Created by ulyx.yang on 2016/11/6.
 */
public class VisitingProjectChangedEvent {
    Project project;

    public VisitingProjectChangedEvent(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }
}
