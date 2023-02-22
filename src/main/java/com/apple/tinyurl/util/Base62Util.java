package com.apple.tinyurl.util;

import org.springframework.stereotype.Component;

@Component
public class Base62Util {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final int BASE62 = ALPHABET.length();
    public  String toBase62(long value) {
        final StringBuilder sb = new StringBuilder(1);
        do {
            sb.insert(0, ALPHABET.charAt((int) (value % BASE62)));
            value /= BASE62;
        } while (value > 0);
        return sb.toString();
    }
}
