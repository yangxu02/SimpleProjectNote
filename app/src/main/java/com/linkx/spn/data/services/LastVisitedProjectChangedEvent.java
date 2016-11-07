package com.linkx.spn.data.services;

import com.linkx.spn.data.models.Project;

/**
 * Created by ulyx.yang on 2016/11/6.
 */
public class LastVisitedProjectChangedEvent {
    public String projectId;

    public LastVisitedProjectChangedEvent(String projectId) {
        this.projectId = projectId;
    }
}
