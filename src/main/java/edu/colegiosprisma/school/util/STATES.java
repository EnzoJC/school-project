package edu.colegiosprisma.school.util;

public enum STATES {
    // Estados para la matrícula
    PRE_ENROLLED(1),
    VALIDATION(2),
    ENROLLED(3),
    // Estados para el pago de matrícula
    PENDING(4),
    WAITING_CONFIRMATION(5),
    APPROVED(6),
    REJECTED(7),
    LATE_PAYMENT(8);

    private int value;

    STATES(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
