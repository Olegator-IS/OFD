package com.edgeapps.personalcabinet.organization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


public class Organization {
    @JsonIgnoreProperties(ignoreUnknown = false)
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

    public Organization() {
    }

    public Organization(long id, String iinBin, String title, String email, String phone, String bic, String paymentAccount, String billingAccountNumber) {
        this.id = id;
        this.iinBin = iinBin;
        this.title = title;
        this.email = email;
        this.phone = phone;
        this.bic = bic;
        this.paymentAccount = paymentAccount;
        this.billingAccountNumber = billingAccountNumber;
    }

    public Organization(Long id, String iinBin, String title, String email, String phone, String bic, String paymentAccount, LegalAddress legalAddress, ActualAddress actualAddress, String billingAccountNumber) {
        this.id = id;
        this.iinBin = iinBin;
        this.title = title;
        this.email = email;
        this.phone = phone;
        this.bic = bic;
        this.paymentAccount = paymentAccount;
        this.legalAddress = legalAddress;
        this.actualAddress = actualAddress;
        this.billingAccountNumber = billingAccountNumber;
    }

    public Organization(String iinBin, String title, String email, String phone, String bic, String paymentAccount, LegalAddress legalAddress, ActualAddress actualAddress, String billingAccountNumber) {

        this.iinBin = iinBin;
        this.title = title;
        this.email = email;
        this.phone = phone;
        this.bic = bic;
        this.paymentAccount = paymentAccount;
        this.legalAddress = legalAddress;
        this.actualAddress = actualAddress;
        this.billingAccountNumber = billingAccountNumber;
    }

    public static Uni<Void> create(PgPool client, Organization organization) throws SQLException {
//Long id_sec = null;
//client.preparedQuery("select nextval('organization_id_seq')").execute().onItem().transform(m->m.iterator().next().getLong("nextval"));
       List<Object> listOfOrg = Arrays.asList(organization.getIinBin(), organization.getTitle(), organization.getEmail(), organization.getPhone(), organization.getBic(), organization.getPaymentAccount(), organization.getBillingAccountNumber());
//       List<Object> listOfAddresses = Arrays.asList(organization.getLegalAddress().getRegionCode(),organization.getLegalAddress().getStreet(),organization.getLegalAddress().getHouse()
//       ,organization.getLegalAddress().getHousing(),organization.getLegalAddress().getApartment());

//        System.out.println(listOfAddresses.toString());


        Uni<RowSet<Row>> insertOne = client
                .preparedQuery("INSERT INTO organization "
                        +
                        "(iin_bin,title,email,phone,bic,payment_account,billing_account_number ) "
                        +
                        "VALUES ($1,$2,$3,$4,$5,$6,$7)")
                .execute(Tuple.tuple(listOfOrg));

//        Uni<RowSet<Row>> insertTwo = client.preparedQuery("INSERT INTO address (organization_id,region_id,street,house,housing,apartment) VALUES ("+1+",$2,$3,$4,$5,$6) RETURNING ORGANIZATION_ID")
//                .execute(Tuple.tuple(listOfAddresses));

    return Uni.combine().all().unis(insertOne).discardItems();


//
    }


    public static Uni<Organization> changeOrg(PgPool client, Organization organization) throws SQLException {

        List<Object> listOfOrg = Arrays.asList(organization.getIinBin(), organization.getTitle(), organization.getEmail(), organization.getPhone(), organization.getBic(), organization.getPaymentAccount(), organization.getBillingAccountNumber());

        return client.preparedQuery("UPDATE organization set iin_bin = $1,title = $2,email = $3,phone = $4,bic = $5,payment_account = $6,billing_account_number = $7  WHERE iin_bin = $1").execute(Tuple.tuple(listOfOrg))
                .onItem().transform(RowSet::iterator)
                .onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);


    }

    public static Uni<Organization> checkOrganization(PgPool client, Organization organization) {
        return client.preparedQuery("SELECT * FROM organization WHERE iin_bin = $1").execute(Tuple.of(organization.getIinBin()))
                .onItem().transform(RowSet::iterator)
                .onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
    }

    private static Organization from(Row row) {
        return new Organization(row.getLong("id"), row.getString("iin_bin"),
                row.getString("title"), row.getString("email"), row.getString("phone"),
                row.getString("bic"), row.getString("payment_account"), row.getString("billing_account_number"));
    }
    //



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIinBin() {
        return iinBin;
    }

    public void setIinBin(String iinBin) {
        this.iinBin = iinBin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public LegalAddress getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(LegalAddress legalAddress) {
        this.legalAddress = legalAddress;
    }

    public ActualAddress getActualAddress() {
        return actualAddress;
    }

    public void setActualAddress(ActualAddress actualAddress) {
        this.actualAddress = actualAddress;
    }

    public String getBillingAccountNumber() {
        return billingAccountNumber;
    }

    public void setBillingAccountNumber(String billingAccountNumber) {
        this.billingAccountNumber = billingAccountNumber;
    }
}
