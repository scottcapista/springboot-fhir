
package com.mongodb.starter.model.patient.v4;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Communication {

    @SerializedName("language")
    @Expose
    private Language language;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

}
