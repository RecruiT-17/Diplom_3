package ru.stellar.utils;

import java.util.UUID;

public class UserGenerator {

    public static String randomEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8) + "@test.ru";
    }

    public static String randomName() {
        return "TestUser_" + UUID.randomUUID().toString().substring(0, 6);
    }
}
