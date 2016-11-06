package com.linkx.spn.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

/**
 * Created by ulyx.yang on 2016/11/6.
 */
@AutoValue
public abstract class TextNote extends Model {
    @JsonProperty("basic")
    public abstract Note basic();
    @JsonProperty("text")
    public abstract String text();

    public static TextNote create(@JsonProperty("basic") Note basic, @JsonProperty("text") String text) {
        return new AutoValue_TextNote(basic, text);
    }

    @Override
    public String identity() {
        return basic().identity();
    }
}
