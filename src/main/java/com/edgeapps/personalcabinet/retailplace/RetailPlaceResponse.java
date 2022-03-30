package com.edgeapps.personalcabinet.retailplace;

import lombok.Data;

@Data
public class RetailPlaceResponse {
    private String errorCode;
    private long id;
    private boolean isSuccessful;

    public RetailPlaceResponse() {
    }

    public RetailPlaceResponse(String errorCode, long id, boolean isSuccessful) {
        this.errorCode = errorCode;
        this.id = id;
        this.isSuccessful = isSuccessful;
    }
}
