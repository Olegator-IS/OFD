package com.edgeapps.personalcabinet.organization;

import lombok.Data;

@Data
public class OrganisationsResponse {
    private String errorCode;
    private long id;
    private boolean isSuccessful;


    public OrganisationsResponse() {
    }
    public OrganisationsResponse(long id, boolean isSuccessful) {
        this.id = id;
        this.isSuccessful = isSuccessful;
    }
    public OrganisationsResponse(String errorCode, long id, boolean isSuccessful) {
        this.errorCode = errorCode;
        this.id = id;
        this.isSuccessful = isSuccessful;
    }
}
