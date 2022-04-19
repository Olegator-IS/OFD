package com.edgeapps.personalcabinet.banks;

import lombok.Data;

@Data
public class BankData {
    private int id;
    private String nameRu;
    private String nameKz;
    private String nameEn;
    private String bic;
    private String paymentAccount;
    private String correspondentAccount;
    private String name;



    public BankData(int id, String nameRu, String nameKz, String nameEn, String bic, String paymentAccount, String correspondentAccount, String name) {
        this.id = id;
        this.nameRu = nameRu;
        this.nameKz = nameKz;
        this.nameEn = nameEn;
        this.bic = bic;
        this.paymentAccount = paymentAccount;
        this.correspondentAccount = correspondentAccount;
        this.name = name;

    }
}
