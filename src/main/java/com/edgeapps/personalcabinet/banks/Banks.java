package com.edgeapps.personalcabinet.banks;



import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;

import lombok.Data;

@Data
public class Banks {
    private int id;
    private String nameRu;
    private String nameKz;
    private String nameEn;
    private String bic;
    private String paymentaccount;
    //


    public Banks(int id, String nameRu, String nameKz, String nameEn, String bic, String paymentaccount, String correspondentAccount) {
        this.id = id;
        this.nameRu = nameRu;
        this.nameKz = nameKz;
        this.nameEn = nameEn;
        this.bic = bic;
        this.paymentaccount = paymentaccount;


    }

    public Banks(int id, String nameRu, String nameKz, String nameEn, String bic, String paymentaccount) {
        this.id = id;
        this.nameRu = nameRu;
        this.nameKz = nameKz;
        this.nameEn = nameEn;
        this.bic = bic;
        this.paymentaccount = paymentaccount;

    }

    public static Multi<Banks> findAll(PgPool client) {
        return client.query("SELECT * FROM banks").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Banks::from);
    }

    public static Banks from(Row row) {


        return new Banks(
                row.getInteger("id"),
                row.getString("name_ru"),
                row.getString("name_kz"),
                row.getString("name_en"),
                row.getString("bic"),
                row.getString("paymentaccount"));
    }
}
