package com.rxjava_operators_java.utility;

import com.rxjava_operators_java.model.User;

import java.util.ArrayList;
import java.util.List;

public class Utilites {


    public static String[] getStringArray(){
        return new String[]{"Apple","Mango","Orange","Banana","Grapes"};
    }

    public static Integer[] getIntegerArray(){
        return new Integer[]{1,2,3,4,5,6};
    }


    public static List<User> getUsers(){
        List<User> users = new ArrayList<>();

        users.add(new User("1","Sateesh"));
        users.add(new User("2","Anand"));
        users.add(new User("3","Kiran"));
        users.add(new User("4","Suresh"));
        users.add(new User("5","Vikram"));
        return users;
    }


    public static List<String> getStringData(){
        List<String> data = new ArrayList<>();

        data.add("ABC");
        data.add("DEF");
        data.add("PQR");
        data.add("LMN");
        data.add("XYZ");

        return data;
    }



}
