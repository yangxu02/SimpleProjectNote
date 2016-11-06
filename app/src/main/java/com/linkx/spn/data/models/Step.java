package com.linkx.spn.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * Created by ulyx.yang on 2016/11/6.
 */
@AutoValue
public abstract class Step extends Model{
    @JsonProperty("id")
    public abstract String id();
    @JsonProperty("name")
    public abstract String name();
    @JsonProperty("createdTime")
    public abstract long createdTime();
    @JsonProperty("priority")
    public abstract String priority();
    @JsonProperty("snippetList")
    public abstract List<NoteSnippet> snippetList();
    @JsonProperty("status")
    public abstract Status status();

    public static Step create(@JsonProperty("id") String id,
                              @JsonProperty("name") String name,
                              @JsonProperty("createdTime") long createdTime,
                              @JsonProperty("priority") String priority,
                              @JsonProperty("snippetList") List<NoteSnippet> snippetList,
                              @JsonProperty("status") Status status) {
        return new AutoValue_Step(id, name, createdTime, priority, snippetList, status);
    }

    @Override
    public String identity() {
        return id();
    }
}
