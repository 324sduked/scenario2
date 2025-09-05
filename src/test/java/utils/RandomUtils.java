package utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static String randomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()_)";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int rnd = ThreadLocalRandom.current().nextInt(chars.length());
            sb.append(chars.charAt(rnd));
        }
        return sb.toString();
    }

}
