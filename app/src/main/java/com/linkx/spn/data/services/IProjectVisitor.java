package com.linkx.spn.data.services;

import com.linkx.spn.data.models.Project;

import com.linkx.spn.data.models.TextNote;
import java.util.List;

/**
 * Created by ulyx.yang on 2016/11/6.
 */
public interface IProjectVisitor {
    void updateLastVisited(Project project);

    Project getLastVisited();

    List<Project> getAllProjects();

    String generateProjectId();

    void saveProject(Project project);

    Project getProjectById(String id);

    String generateTextNoteId();
    void saveTextNote(TextNote textNote);

}
