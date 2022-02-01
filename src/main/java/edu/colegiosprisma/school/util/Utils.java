package edu.colegiosprisma.school.util;

import java.time.LocalDate;
import java.time.Period;

public class Utils {
    public static int getAge(LocalDate birthDate) {
        int year = birthDate.getYear();
        int month = birthDate.getMonthValue();
        int day = birthDate.getDayOfMonth();
        return Period.between(LocalDate.of(year, month, day), LocalDate.now()).getYears();
    }
}
