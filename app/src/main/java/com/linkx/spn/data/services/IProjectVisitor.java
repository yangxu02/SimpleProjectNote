package com.linkx.spn.data.services;

import com.linkx.spn.data.models.Project;

import java.util.List;

/**
 * Created by ulyx.yang on 2016/11/6.
 */
public interface IProjectVisitor {
    void updateLastVisited(Project project);

    Project getLastVisited();

    List<Project> getAllProjects();

}
