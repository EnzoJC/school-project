package edu.colegiosprisma.school.entity.validation;

import edu.colegiosprisma.school.entity.validation.validator.RangeAgeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Permite validar que una edad esté dentro de un rango de edades.
 * <p>
 *     Ejemplo: <code>@RangeAge(min=5, max=18, fieldDate="birthDate")</code>
 * <ul>
 *     <li>El atributo <code>min</code> es el mínimo de edad permitido.</li>
 *     <li>El atributo <code>max</code> es el máximo de edad permitido.</li>
 *     <li>El atributo <code>message</code> es el mensaje de error que se muestra al usuario.</li>
 *     <li>El atributo <code>fieldDate</code> es de tipo <code>LocalDate</code>
 *     y se utiliza para obtener la edad del usuario.</li>
 * </ul>
 */
@Documented
@Constraint(validatedBy = RangeAgeValidator.class)
@Target({ElementType.TYPE}) // ElementType.TYPE permite aplicar a clases y a interfaces
@Retention(RetentionPolicy.RUNTIME)
public @interface RangeAge {
    String message() default "No cumples con el rango de edad";

    int min() default 0;

    int max() default 100;

    String fieldDate();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
