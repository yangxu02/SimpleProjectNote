package com.linkx.spn.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * Created by ulyx.yang on 2016/11/6.
 */
@AutoValue
public abstract class Project extends Model {
    @JsonProperty("id")
    public abstract String id();
    @JsonProperty("name")
    public abstract String name();
    @JsonProperty("createdTime")
    public abstract long createdTime();
    @JsonProperty("snippetList")
    public abstract List<Step> stepList();
    @JsonProperty("status")
    public abstract Status status();

    public static Project create(@JsonProperty("id") String id,
                              @JsonProperty("name") String name,
                              @JsonProperty("createdTime") long createdTime,
                              @JsonProperty("snippetList") List<Step> stepList,
                              @JsonProperty("status") Status status) {
        return new AutoValue_Project(id, name, createdTime, stepList, status);
    }

    @Override
    public String identity() throws MethodNotOverrideException {
        return id();
    }
}
