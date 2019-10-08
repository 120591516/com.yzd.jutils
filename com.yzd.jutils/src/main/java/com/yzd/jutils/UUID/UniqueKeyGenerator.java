package com.yzd.jutils.UUID;

import java.util.UUID;

public class UniqueKeyGenerator {
    public static String generateToken() {
        return UUID.randomUUID ().toString ().replaceAll ("-", "");
    }

    public static String getUUID(String name){
       return UUID.fromString(name).toString();
    }

    public static void main(String[] args) {

        System.out.println(getUUID("fffef582-3d31-496a-a8df-477349c58e13"));
    }
}