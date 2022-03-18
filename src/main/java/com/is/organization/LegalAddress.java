package com.is.organization;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;

import java.util.List;

public class LegalAddress {

    private long regionCode;
    private String street;
    private String house;
    private String housing;
    private String apartment;

    public LegalAddress() {
    }

    public LegalAddress(long regionCode, String street, String house, String housing, String apartment) {
        this.regionCode = regionCode;
        this.street = street;
        this.house = house;
        this.housing = housing;
        this.apartment = apartment;
    }
    public static LegalAddress from(Row row) {
        return new LegalAddress(row.getLong("region_id"), row.getString("street"), row.getString("house"),
                row.getString("housing"), row.getString("apartment"));
    }

    public static Uni<List<LegalAddress>> findAll(PgPool client) {
        return client.query("SELECT * FROM public.address").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(LegalAddress::from).collect().asList();
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
