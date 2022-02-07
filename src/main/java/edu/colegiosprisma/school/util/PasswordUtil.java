package edu.colegiosprisma.school.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);

    public static String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean matches(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }
}
