package com.edgeapps.personalcabinet.organization;



import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import lombok.Data;


@Data
public class OrganisationsForm {

    private int id;
    private String title;
    private String titleKz;
    private String titleEn;
    private String shortTitle;
    private String shortTitleKz;
    private String shortTitleEn;
    //

    public OrganisationsForm(int id, String title, String titleKz, String titleEn, String shortTitle, String shortTitleKz, String shortTitleEn) {
        this.id = id;
        this.title = title;
        this.titleKz = titleKz;
        this.titleEn = titleEn;
        this.shortTitle = shortTitle;
        this.shortTitleKz = shortTitleKz;
        this.shortTitleEn = shortTitleEn;
    }

    public static OrganisationsForm from(Row row) {
        return new OrganisationsForm(
                row.getInteger("id"), row.getString("title"),
                row.getString("title_kz"), row.getString("title_en"),
                row.getString("short_title"), row.getString("short_title_kz"),
                row.getString("short_title_en"));
    }

    public static Multi<OrganisationsForm> findAll(PgPool client) {
        return client.query("SELECT * FROM organisation_form").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(OrganisationsForm::from);
    }
}
