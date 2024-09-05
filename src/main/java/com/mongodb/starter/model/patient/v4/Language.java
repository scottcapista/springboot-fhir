
package com.mongodb.starter.model.patient.v4;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Language {

    @SerializedName("coding")
    @Expose
    private List<Coding__2> coding;
    @SerializedName("text")
    @Expose
    private String text;

    public List<Coding__2> getCoding() {
        return coding;
    }

    public void setCoding(List<Coding__2> coding) {
        this.coding = coding;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
