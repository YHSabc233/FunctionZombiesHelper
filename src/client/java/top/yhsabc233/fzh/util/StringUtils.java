package top.yhsabc233.fzh.util;

@SuppressWarnings("unused")
public class StringUtils {
    public static Integer getNumbersFromString(String string) {
        return Integer.valueOf(string.replaceAll("\\D+",""));
    }
}