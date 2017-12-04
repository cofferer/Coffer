package com.example.lakmal.coffer.Model;

public class BankItem {


    String bankName ;
    String webSite;
    String logoURL ;
    String bankCode ;
    String contact;


        public String getWebSite() {
            return webSite;
        }

        public void setWebSite(String webSite) {
            this.webSite = webSite;
        }
    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }



    public void setBankName(String bankName) {
        this.bankName = bankName;
    }



    public String getBankName() {
        return bankName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
