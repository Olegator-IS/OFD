package com.edgeapps.personalcabinet.organization;

import com.edgeapps.personalcabinet.addresses.ActualAddresses;
import com.edgeapps.personalcabinet.addresses.LegalAddresses;
import com.edgeapps.personalcabinet.addresses.PostalAddresses;
import com.edgeapps.personalcabinet.banks.BankData;
import com.edgeapps.personalcabinet.retailplace.RetailPlaceResponse;
import com.edgeapps.personalcabinet.taxpayer.Taxpayer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.groups.UniSubscribe;
import io.vertx.mutiny.pgclient.PgPool;

import io.vertx.mutiny.sqlclient.PreparedQuery;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import lombok.Data;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Data
public class Organisation {
    @JsonIgnore
    private Integer id;
    private String title;
    private String fullTitle;
    private Integer organisationForm;
    private String legalAddress;
    private String actualAddress;
    private String inn;
    private String ogrn;
    private String kpp;
    private String okved;
    private BankData bankData;
    private String generalManager;
    private Integer responsiblePersonId;
    private GeneralManagerPosition generalManagerPosition;
    private ResponsiblePerson responsiblePerson;
    private Taxpayer taxpayer;
    private String billingAccountNumber;
    private int tenantId;
    private boolean isEmailEnabled;
    private boolean isSmsEnabled;
    private int type;
    private LegalAddresses legalAddresses;
    private PostalAddresses postalAddresses;
    private ActualAddresses actualAddresses;
    private String createdBy;


    public Organisation(Integer id, String title, String fullTitle, Integer organisationForm, String legalAddress, String actualAddress, String inn, String ogrn, String kpp, String okved, String generalManager, Integer responsiblePersonId, String billingAccountNumber, Integer tenantId, boolean isEmailEnabled, boolean isSmsEnabled, Integer type) {
        this.id = id;
        this.title = title;
        this.fullTitle = fullTitle;
        this.organisationForm = organisationForm;
        this.legalAddress = legalAddress;
        this.actualAddress = actualAddress;
        this.inn = inn;
        this.ogrn = ogrn;
        this.kpp = kpp;
        this.okved = okved;
        this.generalManager = generalManager;
        this.responsiblePersonId = responsiblePersonId;
        this.billingAccountNumber = billingAccountNumber;
//        this.tenantId = tenantId;
        this.isEmailEnabled = isEmailEnabled;
        this.isSmsEnabled = isSmsEnabled;
//        this.type = type;

    }


    public static Uni<OrganisationsResponse> create(PgPool client, Organisation organisation) throws SQLException {

        List<Object> listOfLegalAdr = Arrays.
                asList(
                organisation.getLegalAddresses().getRegionCode(),
                organisation.getLegalAddresses().getCity(),
                organisation.getLegalAddresses().getHousing(),
                organisation.getLegalAddresses().getLocalityId(),
                organisation.getLegalAddresses().getApartment(),
                organisation.getLegalAddresses().getIndex(),
                organisation.getLegalAddresses().getHouse(),
                organisation.getLegalAddresses().getFullAddress(),
                organisation.getLegalAddresses().getStreet());

        List<Object> listOfPostalAdr = Arrays.
                asList(
                        organisation.getPostalAddresses().getRegionCode(),
                        organisation.getPostalAddresses().getCity(),
                        organisation.getPostalAddresses().getHousing(),
                        organisation.getPostalAddresses().getLocalityId(),
                        organisation.getPostalAddresses().getApartment(),
                        organisation.getPostalAddresses().getIndex(),
                        organisation.getPostalAddresses().getHouse(),
                        organisation.getPostalAddresses().getFullAddress(),
                        organisation.getPostalAddresses().getStreet());

        List<Object> listOfActualalAdr = Arrays.
                asList(
                        organisation.getActualAddresses().getRegionCode(),
                        organisation.getActualAddresses().getCity(),
                        organisation.getActualAddresses().getHousing(),
                        organisation.getActualAddresses().getLocalityId(),
                        organisation.getActualAddresses().getApartment(),
                        organisation.getActualAddresses().getIndex(),
                        organisation.getActualAddresses().getHouse(),
                        organisation.getActualAddresses().getFullAddress(),
                        organisation.getActualAddresses().getStreet());


        return client.withTransaction(conn -> {


            Uni<RowSet<Row>> insertOne = conn.preparedQuery("INSERT INTO addresses_ (region_code,city,housing,locality_id,apartment,index,house,full_address,street)"
                                    +
                            " VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9) RETURNING id")
                             .execute((Tuple.tuple(Arrays.asList(
                                     organisation.getActualAddresses().getRegionCode(),
                                     organisation.getActualAddresses().getCity(),
                                     organisation.getActualAddresses().getHousing(),
                                     organisation.getActualAddresses().getLocalityId(),
                                     organisation.getActualAddresses().getApartment(),
                                     organisation.getActualAddresses().getIndex(),
                                     organisation.getActualAddresses().getHouse(),
                                     organisation.getActualAddresses().getFullAddress(),
                                     organisation.getActualAddresses().getStreet()))));






//            RowSet<Row> test1 = insertOne.onItem().transformToUni(test -> test.iterator().next().getLong("id"));


            Uni<RowSet<Row>> insertTwo = conn.preparedQuery("INSERT INTO addresses_ (region_code,city,housing,locality_id,apartment,index,house,full_address,street)"
                            +
                            " VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9) RETURNING id")
                    .execute((Tuple.tuple(Arrays.asList(
                            organisation.getActualAddresses().getRegionCode(),
                            organisation.getActualAddresses().getCity(),
                            organisation.getActualAddresses().getHousing(),
                            organisation.getActualAddresses().getLocalityId(),
                            organisation.getActualAddresses().getApartment(),
                            organisation.getActualAddresses().getIndex(),
                            organisation.getActualAddresses().getHouse(),
                            organisation.getActualAddresses().getFullAddress(),
                            organisation.getActualAddresses().getStreet()))));

            insertTwo.onItem().transform(id -> id.iterator().next().getLong("id")).subscribe().toString();

            Uni<RowSet<Row>> insertThree = conn.preparedQuery("INSERT INTO addresses_ (region_code,city,housing,locality_id,apartment,index,house,full_address,street)"
                            +
                            " VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9) RETURNING id")
                    .execute((Tuple.tuple(Arrays.asList(
                            organisation.getPostalAddresses().getRegionCode(),
                            organisation.getPostalAddresses().getCity(),
                            organisation.getPostalAddresses().getHousing(),
                            organisation.getPostalAddresses().getLocalityId(),
                            organisation.getPostalAddresses().getApartment(),
                            organisation.getPostalAddresses().getIndex(),
                            organisation.getPostalAddresses().getHouse(),
                            organisation.getPostalAddresses().getFullAddress(),
                            organisation.getPostalAddresses().getStreet()))));




            Uni<RowSet<Row>> insertFour = conn.preparedQuery("INSERT INTO organisation_ "
                            +
                "(legal_address_id,postal_address_id,actual_address_id,"
                +
                "short_title,title,inn,ogrn,kpp,okved,form_id,billing_account_number,bank,bic,payment_account,correspondent_account,general_manager_id,"
                +
                "contact_phone,responsible_person_email,"
                +
                "created_by,is_email_enabled,is_sms_enabled)"
                +
                " VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,$13,$14,$15,$16,$17,$18,$19,true,true) returning id")
                .execute(Tuple.tuple(Arrays.asList(
//                        insertTwo.onItem().transform(id -> id.iterator().next().getLong("id")).subscribe().toString(),
//                        insertTwo.onItem().transform(id -> id.iterator().next().getLong("id")).subscribe().toString(),
//                        insertTwo.onItem().transform(id -> id.iterator().next().getLong("id")).subscribe().toString(),
                        106,107,108 ,
                        organisation.getTitle(), organisation.getFullTitle(), organisation.getInn(),
                        organisation.getOgrn(), organisation.getKpp(), organisation.getOkved(),
                        organisation.getOrganisationForm(), organisation.getBillingAccountNumber(), organisation.getBankData().getName(),
                        organisation.getBankData().getBic(), organisation.getBankData().getPaymentAccount(),
                        organisation.getBankData().getCorrespondentAccount(), organisation.getGeneralManagerPosition().getId(),
                        organisation.getResponsiblePerson().getPhone(), organisation.getResponsiblePerson().getEmail(),
                        organisation.getCreatedBy())));


            return Uni.combine().all().unis(insertOne, insertTwo, insertThree, insertFour)
                    .discardItems().onItem().transform(pgRowSet -> new OrganisationsResponse(null, 0, true))
                    .onFailure().invoke(error -> new OrganisationsResponse(error.toString(), 0, false));
        });


    }


    private static LegalAddresses from1(Row row) {
        return new LegalAddresses(
                row.getInteger("id"),
                row.getString("name"), row.getString("name"),
                row.getString("name"), row.getString("name"),
                row.getString("name"), row.getString("name"),
                row.getString("name"), row.getString("name"),
                row.getString("name"), row.getString("name"),
                row.getString("name"), row.getDouble("name"),
                row.getDouble("name"),
                row.getString("name"));
    }



    public static Uni<Integer> changeOrg(PgPool client, Organisation organisation) throws SQLException {
        List<Object> listOfOrg = Arrays.asList();

        PreparedQuery<RowSet<Row>> updatePrepared = client.preparedQuery("UPDATE organization set iin_bin = $1, title = $2, email = $3, phone = $4, "
                + "bic = $5, payment_account = $6, billing_account_number = $7 WHERE iin_bin = $1");

        Uni<RowSet<Row>> rowSet = updatePrepared.execute(Tuple.tuple(listOfOrg));

        Uni<Integer> totalAffected = rowSet.onItem().transform(res -> {
            int total = 0;
            do {
                total += res.rowCount();
            } while ((res = res.next()) != null);
            System.out.println(total);
            return total;
        });

        return totalAffected;
    }




    public static Multi<Organisation> checkOrganization(PgPool client) {
        return client.query("SELECT * FROM organisation_ where id = 118").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Organisation::from);
    }

    


    private static Organisation from(Row row) {
        return new Organisation(

                       row.getInteger("id"),
                       row.getString("short_title"),
                       row.getString("title"),
                       row.getInteger("form_id"),
                       row.getString("inn"),
                     row.getString("ogrn"),
                       row.getString("kpp"),
                       row.getString("okved"),
                       row.getString("billing_account_number"),

                       row.getString("inn"),
                       row.getString("inn"),
                       row.getInteger("id"),
                       row.getString("inn"),
                       row.getInteger("id"),
                       row.getBoolean("is_sms_enabled"),
                       row.getBoolean("is_email_enabled"),
                       row.getInteger("id"));



    }

}



