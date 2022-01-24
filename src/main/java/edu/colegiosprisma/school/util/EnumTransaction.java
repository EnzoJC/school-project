package edu.colegiosprisma.school.util;

public enum EnumTransaction {
    LATE_PAYMENT(6),
    PAID(7),
    VALIDATION(8);

    private int value;

    EnumTransaction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
