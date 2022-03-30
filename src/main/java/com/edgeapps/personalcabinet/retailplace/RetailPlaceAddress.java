package com.edgeapps.personalcabinet.retailplace;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import lombok.Data;

@Data
public class RetailPlaceAddress {
    private long retailPlaceId;
    private long regionCode;
    private String city;
    private String localityId;
    private String apartment;
    private String house;
    private String region;
    private String street;

    public static RetailPlaceAddress from(Row row) {
        RetailPlaceAddress address = new RetailPlaceAddress();
        address.setRetailPlaceId(row.getLong("retail_place_id"));
        address.setRegionCode(row.getLong("region_code"));
        address.setCity(row.getString("city"));
        address.setLocalityId(row.getString("locality_id"));
        address.setApartment(row.getString("apartment"));
        address.setHouse(row.getString("house"));
        address.setRegion(row.getString("region"));
        address.setStreet(row.getString("street"));
        return address;
    }

    public static Uni<RetailPlaceAddress> get(PgPool client, long id) {
        return client.preparedQuery("select * from retail_place_address where retail_place_id = $1")
                .execute(Tuple.of(id)).onItem().transform(RowSet::iterator)
                .onItem().transform(iterator -> iterator.hasNext() ? RetailPlaceAddress.from(iterator.next()) : null);
    }
}
