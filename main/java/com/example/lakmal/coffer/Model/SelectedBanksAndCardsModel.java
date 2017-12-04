package com.example.lakmal.coffer.Model;

import java.util.ArrayList;

public class SelectedBanksAndCardsModel {

    String bankName;
    ArrayList<String> cardNames = new ArrayList<String>();


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public ArrayList<String> getCardNames() {
        return cardNames;
    }

    public void setCardNames(ArrayList<String> cardNames) {
        this.cardNames = cardNames;
    }
}
