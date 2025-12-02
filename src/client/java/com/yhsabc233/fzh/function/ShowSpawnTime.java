package com.yhsabc233.fzh.function;

public class ShowSpawnTime {
    public static String getRoundInfo(int gameRound) {
        return switch (gameRound) {
            case 1 -> "bro刚开局";
            case 2 -> "bro貌似玩了一个回合";
            case 3 -> "1+2=?";
            case 4 -> "emm,3+1=?";
            case 5 -> "555";
            case 6 -> "666";
            case 7 -> "6+1";
            case 8 -> "e?";
            case 9 -> "⑨";
            case 10 -> "神秘 到两位数了";
            default -> "Unkown";
        };
    }
}
