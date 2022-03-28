package com.edgeapps.personalcabinet.organization;

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


    public long getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(long regionCode) {
        this.regionCode = regionCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
}
