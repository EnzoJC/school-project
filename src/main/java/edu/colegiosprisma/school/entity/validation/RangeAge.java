package edu.colegiosprisma.school.entity.validation;

import edu.colegiosprisma.school.entity.validation.validator.RangeAgeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RangeAgeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RangeAge {
    String message() default "Debe ser menor a 18 añoss";

    int min();

    int max();

    String field();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
