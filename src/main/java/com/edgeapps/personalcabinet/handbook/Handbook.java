package com.edgeapps.personalcabinet.handbook;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import lombok.Data;

@Data
public class Handbook {
    private long id;
    private String name;

    public Handbook(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Handbook from(Row row) {
        return new Handbook(row.getLong("id"), row.getString("name"));
    }

    public static Multi<Handbook> getRegion(PgPool client) {
        return client.query("SELECT id, name FROM region ORDER BY id").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Handbook::from);
    }
}
