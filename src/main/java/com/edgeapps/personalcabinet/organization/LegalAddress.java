package com.edgeapps.personalcabinet.organization;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import lombok.Data;

import java.util.List;

@Data
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
}
