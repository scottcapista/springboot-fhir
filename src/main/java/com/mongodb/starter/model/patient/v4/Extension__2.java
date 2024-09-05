
package com.mongodb.starter.model.patient.v4;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Extension__2 {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("extension")
    @Expose
    private List<Extension__3> extension;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Extension__3> getExtension() {
        return extension;
    }

    public void setExtension(List<Extension__3> extension) {
        this.extension = extension;
    }

}
