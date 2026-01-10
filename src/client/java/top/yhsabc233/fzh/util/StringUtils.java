package top.yhsabc233.fzh.util;

public class StringUtils {
    public static Integer getNumbersFromString(String string) {
        return Integer.valueOf(string.replaceAll("\\D+",""));
    }
}