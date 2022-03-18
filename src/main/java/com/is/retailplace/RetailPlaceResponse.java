package com.is.retailplace;

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

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
