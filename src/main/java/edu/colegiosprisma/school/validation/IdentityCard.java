package edu.colegiosprisma.school.validation;

import edu.colegiosprisma.school.validation.validator.IndentityCardValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Permite validar que el tipo de documento y la longitud del número de documento sean correctos.
 * <p>
 * Ejemplo: Un DNI tiene 8 caracteres, una cédula tiene 10 caracteres, una pasaporte tiene 9 caracteres.
 * <p>
 * Ejemplo: <code>@IdentityCard(fieldType = "documentType", fieldLength = "documentNumber")</code>
 * <ul>
 *     <li>El atributo <code>fieldType</code> es para el nombre del objeto de tipo de documento (DNI, NIE, PASAPORTE, etc),
 *     este objeto contiene la longitud del número de documento.</li>
 *     <li>El atributo <code>fieldLength</code> es para el nombre del objeto de número de documento. El número que se validará</li>
 * </ul>
 */
@Documented
@Constraint(validatedBy = IndentityCardValidator.class)
@Target({ElementType.TYPE}) // ElementType.TYPE permite aplicar a clases y a interfaces
@Retention(RetentionPolicy.RUNTIME)
public @interface IdentityCard {
    String message() default "No cumple el tamaño";

    String fieldType();

    String fieldLength();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
