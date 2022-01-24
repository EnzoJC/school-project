package edu.colegiosprisma.school.util;

public enum EnumEnrollment {
    WAITING(1),
    PRE_REGISTERED(2),
    VALIDATION(3),
    ENROLLED(4),
    PENDING_TO_PAY(5);

    private int value;

    EnumEnrollment(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
