package com.edgeapps.personalcabinet.organization;

import lombok.Data;

@Data
public class ActualAddress {
    private long regionCode;
    private String street;
    private String house;
    private String housing;
    private String apartment;

    public ActualAddress() {
    }

    public ActualAddress(long regionCode, String street, String house, String housing, String apartment) {
        this.regionCode = regionCode;
        this.street = street;
        this.house = house;
        this.housing = housing;
        this.apartment = apartment;
    }
}
