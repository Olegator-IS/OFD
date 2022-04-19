package com.edgeapps.personalcabinet.addresses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.mutiny.sqlclient.Row;
import lombok.Data;

@Data
public class LegalAddresses {
    @JsonIgnore
    private int id;
    private String regionCode;
    private String index;
    private String area;
    private String city;
    private String village;
    private String street;
    private String house;
    private String housing;
    private String apartment;
    private String fullAddress;
    private String localityId;
    private Double latitude;
    private Double longitude;
    private String geonimId;

    public LegalAddresses(int id, String regionCode, String index, String area, String city, String village, String street, String house, String housing, String apartment, String fullAddress, String localityId, Double latitude, Double longitude, String geonimId) {
        this.id = id;
        this.regionCode = regionCode;
        this.index = index;
        this.area = area;
        this.city = city;
        this.village = village;
        this.street = street;
        this.house = house;
        this.housing = housing;
        this.apartment = apartment;
        this.fullAddress = fullAddress;
        this.localityId = localityId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.geonimId = geonimId;
    }

    public static LegalAddresses from1(Row row) {
        return new LegalAddresses(
                row.getInteger("id"),
                row.getString("name"),
                row.getString("name"),
                row.getString("name"),
                row.getString("name"),
                row.getString("name"),
                row.getString("name"),
                row.getString("name"),
                row.getString("name"),
                row.getString("name"),
                row.getString("name"),
                row.getString("name"),
                row.getDouble("name"),
                row.getDouble("name"),
                row.getString("name"));
    }

}
