package com.edgeapps.personalcabinet.addresses;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PostalAddresses {
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


}
