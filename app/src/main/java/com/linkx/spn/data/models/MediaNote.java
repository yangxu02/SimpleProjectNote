package com.linkx.spn.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

/**
 * Created by ulyx.yang on 2016/11/6.
 */
@AutoValue
public abstract class MediaNote extends Model {
    @JsonProperty("basic")
    public abstract Note basic();
    @JsonProperty("url")
    public abstract String url();
    @JsonProperty("path")
    public abstract String path();

    public static MediaNote create(@JsonProperty("basic") Note basic,
                                   @JsonProperty("url") String url,
                                   @JsonProperty("path") String path) {
        return new AutoValue_MediaNote(basic, url, path);
    }

    @Override
    public String identity() {
        return basic().identity();
    }

}
