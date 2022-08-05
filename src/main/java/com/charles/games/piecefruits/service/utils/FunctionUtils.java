package com.charles.games.piecefruits.service.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class FunctionUtils {

    private FunctionUtils() {
    }

    public static String getRandomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public static String getRandomString(Integer size) {
        return RandomStringUtils.randomAlphabetic(size);
    }

    public static String getRandomNumeric() {
        return RandomStringUtils.randomNumeric(10000);
    }

    public static String getRandomNumeric(Integer size) {
        return RandomStringUtils.randomNumeric(size);
    }
}
