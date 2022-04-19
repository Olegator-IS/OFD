package com.edgeapps.personalcabinet.positions;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import lombok.Data;


@Data
public class Positions {
    private int id;
    private String nameRu;
    private String nameKz;
    private String nameEn;


    public Positions(int id, String nameRu, String nameKz, String nameEn) {
        this.id = id;
        this.nameRu = nameRu;
        this.nameKz = nameKz;
        this.nameEn = nameEn;
    }


    public static Positions from(Row row) {
        return new Positions(
                row.getInteger("id"), row.getString("name_ru"),
                row.getString("name_kz"), row.getString("name_en"));
    }


    public static Multi<Positions> findAll(PgPool client) {
        return client.query("SELECT * FROM POSITIONS").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Positions::from);
    }
}
