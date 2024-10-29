package com.project.url_shortening_service.util;

import java.util.regex.Pattern;

public class Base62Encoder {

    private static final char[] BASE62_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final Pattern BASE62_PATTERN = Pattern.compile("[0-9a-zA-Z]");

    public static String getEncodedId(String uuid) {
        long decimalValue = convertHexToDecimal(uuid);

        StringBuilder base62String = new StringBuilder();
        while (decimalValue > 0) {
            int remainder = (int) (decimalValue % 62);
            base62String.append(BASE62_CHARS[remainder]);
            decimalValue /= 62;
        }
        return base62String.reverse().toString();
    }

    private static long convertHexToDecimal(String hex) {
        return Long.parseLong(hex, 16);
    }

    public static boolean isValidBase62Character(char c) {
        return BASE62_PATTERN.matcher(String.valueOf(c)).matches();
    }
}
