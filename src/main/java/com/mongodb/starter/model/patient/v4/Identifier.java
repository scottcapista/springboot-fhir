
package com.mongodb.starter.model.patient.v4;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Identifier {

    @SerializedName("system")
    @Expose
    private String system;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("type")
    @Expose
    private Type type;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
