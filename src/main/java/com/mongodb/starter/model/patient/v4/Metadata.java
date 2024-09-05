
package com.mongodb.starter.model.patient.v4;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Metadata {

    @SerializedName("documentVersion")
    @Expose
    private String documentVersion;
    @SerializedName("fhirVersion")
    @Expose
    private String fhirVersion;
    @SerializedName("lastUpdate")
    @Expose
    private String lastUpdate;
    @SerializedName("tenant_id")
    @Expose
    private String tenantId;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("searchParameters")
    @Expose
    private List<SearchParameter> searchParameters;

    public String getDocumentVersion() {
        return documentVersion;
    }

    public void setDocumentVersion(String documentVersion) {
        this.documentVersion = documentVersion;
    }

    public String getFhirVersion() {
        return fhirVersion;
    }

    public void setFhirVersion(String fhirVersion) {
        this.fhirVersion = fhirVersion;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<SearchParameter> getSearchParameters() {
        return searchParameters;
    }

    public void setSearchParameters(List<SearchParameter> searchParameters) {
        this.searchParameters = searchParameters;
    }

}
