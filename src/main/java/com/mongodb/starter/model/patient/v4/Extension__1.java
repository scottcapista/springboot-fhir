
package com.mongodb.starter.model.patient.v4;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Extension__1 {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("valueCoding")
    @Expose
    private ValueCoding valueCoding;
    @SerializedName("valueString")
    @Expose
    private String valueString;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ValueCoding getValueCoding() {
        return valueCoding;
    }

    public void setValueCoding(ValueCoding valueCoding) {
        this.valueCoding = valueCoding;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

}
