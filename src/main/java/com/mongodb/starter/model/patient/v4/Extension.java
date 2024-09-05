
package com.mongodb.starter.model.patient.v4;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Extension {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("extension")
    @Expose
    private List<Extension__1> extension;
    @SerializedName("valueString")
    @Expose
    private String valueString;
    @SerializedName("valueCode")
    @Expose
    private String valueCode;
    @SerializedName("valueAddress")
    @Expose
    private ValueAddress valueAddress;
    @SerializedName("valueDecimal")
    @Expose
    private Double valueDecimal;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Extension__1> getExtension() {
        return extension;
    }

    public void setExtension(List<Extension__1> extension) {
        this.extension = extension;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public String getValueCode() {
        return valueCode;
    }

    public void setValueCode(String valueCode) {
        this.valueCode = valueCode;
    }

    public ValueAddress getValueAddress() {
        return valueAddress;
    }

    public void setValueAddress(ValueAddress valueAddress) {
        this.valueAddress = valueAddress;
    }

    public Double getValueDecimal() {
        return valueDecimal;
    }

    public void setValueDecimal(Double valueDecimal) {
        this.valueDecimal = valueDecimal;
    }

}
