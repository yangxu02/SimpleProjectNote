package com.linkx.spn.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

/**
 * Created by ulyx.yang on 2016/11/6.
 */
@AutoValue
public abstract class MediaNote extends Model {
    public enum Type {
        video,
        image
    }

    @JsonProperty("basic")
    public abstract Note basic();
    @JsonProperty("url")
    public abstract String url();
    @JsonProperty("path")
    public abstract String path();
    @JsonProperty("type")
    public abstract Type type();

    public static MediaNote create(@JsonProperty("basic") Note basic,
                                   @JsonProperty("url") String url,
                                   @JsonProperty("path") String path,
                                   @JsonProperty("type") Type type
    ) {
        return new AutoValue_MediaNote(basic, url, path, type);
    }

    @Override
    public String identity() {
        return basic().identity();
    }

}
