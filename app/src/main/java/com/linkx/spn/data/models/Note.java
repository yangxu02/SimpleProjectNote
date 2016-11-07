package com.linkx.spn.data.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

/**
 * Created by ulyx.yang on 2016/11/6.
 */
@AutoValue
public abstract class Note extends Model {
    @JsonProperty("id")
    public abstract String id();
    @JsonProperty("createdTime")
    public abstract long createdTime();

    @JsonCreator
    public static Note create(@JsonProperty("id") String id, @JsonProperty("createdTime") long createdTime) {
        return new AutoValue_Note(id, createdTime);
    }

    @Override
    public String identity() {
        return id();
    }

}
