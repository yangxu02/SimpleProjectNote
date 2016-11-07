package com.linkx.spn.data.services;

import com.linkx.spn.data.models.Project;
import com.linkx.spn.data.models.TextNote;
import java.util.List;

/**
 * Created by ulyx.yang@yeahmobi.com on 2016/11/7.
 */
public class ProjectVisitingService implements IProjectVisitor {

    final static ProjectVisitingService instance = new ProjectVisitingService();

    public static ProjectVisitingService worker() {
        return instance;
    }

    private ProjectVisitingService() {
    }

    @Override
    public void updateLastVisited(Project project) {

    }

    @Override
    public Project getLastVisited() {
        return null;
    }

    @Override
    public List<Project> getAllProjects() {
        return null;
    }

    @Override
    public String generateProjectId() {
        return null;
    }

    @Override
    public void saveProject(Project project) {

    }

    @Override
    public Project getProjectById(String id) {
        return null;
    }

    @Override
    public String generateTextNoteId() {
        return null;
    }

    @Override
    public void saveTextNote(TextNote textNote) {

    }
}
