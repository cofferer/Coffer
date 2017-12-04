package com.example.lakmal.coffer.Model;

import com.example.lakmal.coffer.UI.SignIn;

import java.util.ArrayList;
import java.util.List;

public class UserData {


    public static List<UserItem> getUserData() {

        List<UserItem> data = new ArrayList<>();
        UserItem       item = new UserItem();
        SignIn            s = new SignIn();


            data.add(item);

        return data;
        }



    }

