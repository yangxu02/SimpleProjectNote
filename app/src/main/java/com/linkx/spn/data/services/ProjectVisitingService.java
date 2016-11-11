package com.linkx.spn.data.services;

import android.util.Log;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.io.Files;
import com.linkx.spn.data.models.Model;
import com.linkx.spn.data.models.Project;
import com.linkx.spn.data.models.Status;
import com.linkx.spn.data.models.Step;
import com.linkx.spn.data.models.TextNote;
import com.linkx.spn.utils.IOUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ulyx.yang@yeahmobi.com on 2016/11/7.
 */
public class ProjectVisitingService {

    final static String DB_DIR = ".spn/db";

    final static ProjectVisitingService instance = new ProjectVisitingService();

    public static ProjectVisitingService worker() {
        return instance;
    }

    private ProjectVisitingService() {
    }

    public List<Project> getAllProjects() {
        return null;
    }

    public void getProjectById(String id, Subscriber<Project> subscriber) {
        Observable.just(null)
            .flatMap(f -> {
                Project project = null;
                try {
                    project = getProjectById(id);
                    return Observable.just(project);
                } catch (IOException e) {
                    throw OnErrorThrowable.from(e);
                }
            })
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber);
    }


    public void getProjectToShow(Subscriber<Project> subscriber) {
           Observable.just(null)
            .flatMap(f -> {
                Project project = null;
                try {
                    project = getLastVisited();
                    return Observable.just(project);
                } catch (Exception e) {
                    Log.w("", e);
                }

                try {
                    project = getFirstSeen();
                    return Observable.just(project);
                } catch (IOException e) {
                    throw OnErrorThrowable.from(e);
                }
            })
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber);
    }

    public void saveProject(String projectName, String stepStr, Subscriber<Project> subscriber) {
        Observable.just(null)
            .flatMap(f -> {
                try {
                    String type = "proj";
                    long id = generateId(type);
                    Project project = Project.create("" + id, projectName,
                        System.currentTimeMillis(), parseStepsFromInput(stepStr),
                        Status.pending);
                    save(project, type);
                    return Observable.just(project);
                } catch (IOException | Model.MethodNotOverrideException e) {
                    throw OnErrorThrowable.from(e);
                }
            })
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber);
    }

    public String generateTextNoteId() {
        return null;
    }

    public String generateStepId() {
        return null;
    }


    public void saveTextNote(TextNote textNote) {

    }

    private void save(Model model, String type) throws IOException, Model.MethodNotOverrideException {
        String fileName = IOUtil.dbFileName(DB_DIR, type + "/" + model.identity());
        IOUtil.writeLine(fileName, model.toJson());
    }

    private long generateId(String type) throws IOException {
        String fileName = IOUtil.dbFileName(DB_DIR, type + "/_seq");
        File file = new File(fileName);
        long id = 1L;
        if (!file.exists()) {
            Files.createParentDirs(file);
        } else {
            long current = Long.parseLong(Files.toString(file, Charsets.UTF_8));
            id = current + 1;
        }
        Files.write("" + id, file, Charsets.UTF_8);
        return id;
    }

     private List<Step> parseStepsFromInput(String projectSteps) {
         Iterable<String> steps = Splitter.on('\n').split(projectSteps);
         List<Step> stepList = new ArrayList<>();
         ProjectVisitingService pvs = ProjectVisitingService.worker();
         int i = 0;
         long now = System.currentTimeMillis();
         for (String step : steps) {
             ++i;
             String id = pvs.generateStepId();
             Step s = Step.create(id, step, now, i + "", Collections.emptyList(), Status.pending);
             stepList.add(s);
         }
        return stepList;
    }

    public void updateLastVisited(Project project) throws IOException {
        String fileName = IOUtil.dbFileName(DB_DIR, "proj" + "/_last");
        IOUtil.writeLine(fileName, project.id());
    }


    public Project getLastVisited() throws IOException {
        String fileName = IOUtil.dbFileName(DB_DIR, "proj" + "/_last");
        String id = IOUtil.readFirstLine(fileName);
        return getProjectById(id);
    }

    public Project getFirstSeen() throws IOException {
        String projectDir = IOUtil.dbFileName(DB_DIR, "proj");
        Iterable<File> files = Files.fileTreeTraverser()
            .children(new File(projectDir));
        for (File file : files) {
            String fileName = file.getName();
            if ("_seq".equals(fileName)
                || "_last".equals(fileName)
                || ".".equals(fileName)
                || "..".equals(fileName)) {
                continue;
            }
            return getProjectById(fileName);
        }
        return null;
    }


    private String projectFileName(String id) {
        return IOUtil.dbFileName(DB_DIR, "proj" + "/" + id);
    }

    public Project getProjectById(String id) throws IOException {
        String content = Files.toString(new File(projectFileName(id)), Charsets.UTF_8);
        return Project.fromJson(content, Project.class);
    }




}
