package com.linkx.spn.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * Created by ulyx.yang on 2016/11/6.
 */
@AutoValue
public abstract class NoteSnippet extends Model{
    @JsonProperty("id")
    public abstract String id();
    @JsonProperty("day")
    public abstract String day();
    @JsonProperty("createdTime")
    public abstract long createdTime();
    @JsonProperty("textNoteList")
    public abstract List<TextNote> textNodeList();
    @JsonProperty("imageNoteList")
    public abstract List<MediaNote> imageNodeList();
    @JsonProperty("videoNoteList")
    public abstract List<MediaNote> videoNodeList();

    public static NoteSnippet create(@JsonProperty("id") String id,
                                     @JsonProperty("day") String day,
                                     @JsonProperty("createdTime") long createdTime,
                                     @JsonProperty("textNoteList") List<TextNote> textNodeList,
                                     @JsonProperty("imageNoteList") List<MediaNote> imageNodeList,
                                     @JsonProperty("videoNoteList") List<MediaNote> videoNodeList) {
        return new AutoValue_NoteSnippet(id, day, createdTime, textNodeList, imageNodeList, videoNodeList);
    }

    @Override
    public String identity() {
        return id();
    }
}
