package com.edgeapps.personalcabinet.organization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import lombok.Data;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Data
public class Organization {
    @JsonIgnore
    private long id;
    private String iinBin;
    private String title;
    private String email;
    private String phone;
    private String bic;
    private String paymentAccount;
    private LegalAddress legalAddress;
    private ActualAddress actualAddress;
    private String billingAccountNumber;

    public static Uni<Void> create(PgPool client, Organization organization) throws SQLException {
//Long id_sec = null;
//client.preparedQuery("select nextval('organization_id_seq')").execute().onItem().transform(m->m.iterator().next().getLong("nextval"));
        List<Object> listOfOrg = Arrays.asList(organization.getIinBin(), organization.getTitle(),
                organization.getEmail(), organization.getPhone(),
                organization.getBic(), organization.getPaymentAccount(),
                organization.getBillingAccountNumber());
//       List<Object> listOfAddresses = Arrays.asList(organization.getLegalAddress().getRegionCode(),organization.getLegalAddress().getStreet(),
//       organization.getLegalAddress().getHouse()
//       ,organization.getLegalAddress().getHousing(),organization.getLegalAddress().getApartment());

//        System.out.println(listOfAddresses.toString());

        Uni<RowSet<Row>> insertOne = client
                .preparedQuery("INSERT INTO organization "
                        +
                        "(iin_bin,title,email,phone,bic,payment_account,billing_account_number ) "
                        +
                        "VALUES ($1,$2,$3,$4,$5,$6,$7)")
                .execute(Tuple.tuple(listOfOrg));

//        Uni<RowSet<Row>> insertTwo = client.preparedQuery("INSERT INTO address (organization_id,region_id,street,house,housing,apartment)
//        VALUES ("+1+",$2,$3,$4,$5,$6) RETURNING ORGANIZATION_ID")
//                .execute(Tuple.tuple(listOfAddresses));

        return Uni.combine().all().unis(insertOne).discardItems();
    }

    public static Uni<Organization> changeOrg(PgPool client, Organization organization) throws SQLException {
        List<Object> listOfOrg = Arrays.asList(organization.getIinBin(), organization.getTitle(),
                organization.getEmail(), organization.getPhone(),
                organization.getBic(), organization.getPaymentAccount(),
                organization.getBillingAccountNumber());
        return client.preparedQuery("UPDATE organization set iin_bin = $1, title = $2, email = $3, phone = $4, "
                        + "bic = $5, payment_account = $6, billing_account_number = $7 WHERE iin_bin = $1").execute(Tuple.tuple(listOfOrg))
                .onItem().transform(RowSet::iterator)
                .onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
    }

    public static Uni<Organization> checkOrganization(PgPool client, Organization organization) {
        return client.preparedQuery("SELECT * FROM organization WHERE iin_bin = $1").execute(Tuple.of(organization.getIinBin()))
                .onItem().transform(RowSet::iterator)
                .onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
    }

    private static Organization from(Row row) {
        Organization org = new Organization();
        org.setId(row.getLong("id"));
        org.setIinBin(row.getString("iin_bin"));
        org.setTitle(row.getString("title"));
        org.setEmail(row.getString("email"));
        org.setPhone(row.getString("phone"));
        org.setBic(row.getString("bic"));
        org.setPaymentAccount(row.getString("payment_account"));
        org.setBillingAccountNumber(row.getString("billing_account_number"));
        return org;
    }
}
