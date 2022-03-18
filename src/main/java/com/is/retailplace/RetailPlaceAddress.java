package com.is.retailplace;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

public class RetailPlaceAddress {
    private long retailPlaceId;
    private long regionCode;
    private String city;
    private String localityId;
    private String apartment;
    private String house;
    private String region;
    private String street;

    public RetailPlaceAddress() {
    }

    public static RetailPlaceAddress from(Row row) {
        return new RetailPlaceAddress(row.getLong("retail_place_id"),
                row.getLong("region_code"), row.getString("city"),
                row.getString("locality_id"), row.getString("apartment"), row.getString("house"),
                row.getString("region"), row.getString("street"));
    }

    public static Uni<RetailPlaceAddress> get(PgPool client, long id){
        return client.preparedQuery("select * from retail_place_address where retail_place_id = $1")
                .execute(Tuple.of(id)).onItem().transform(RowSet::iterator)
                .onItem().transform(iterator -> iterator.hasNext() ? RetailPlaceAddress.from(iterator.next()) : null);
    }

    public RetailPlaceAddress(long retailPlaceId, long regionCode, String city, String localityId, String apartment, String house, String region, String street) {
        this.retailPlaceId = retailPlaceId;
        this.regionCode = regionCode;
        this.city = city;
        this.localityId = localityId;
        this.apartment = apartment;
        this.house = house;
        this.region = region;
        this.street = street;
    }

    public long getRetailPlaceId() {
        return retailPlaceId;
    }

    public void setRetailPlaceId(long retailPlaceId) {
        this.retailPlaceId = retailPlaceId;
    }

    public long getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(long regionCode) {
        this.regionCode = regionCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocalityId() {
        return localityId;
    }

    public void setLocalityId(String localityId) {
        this.localityId = localityId;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}