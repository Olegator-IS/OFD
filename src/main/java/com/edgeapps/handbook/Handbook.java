package com.edgeapps.handbook;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;

public class Handbook {

    private long id;
    private String name;

    public Handbook() {
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
