package com.yhsabc233.fzh.utils;

public class StringUtils {
    public static Integer getNumbersFromString(String string) {
        return Integer.valueOf(string.replaceAll("\\D+",""));
    }
}