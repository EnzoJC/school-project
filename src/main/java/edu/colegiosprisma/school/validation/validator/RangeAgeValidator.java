package edu.colegiosprisma.school.validation.validator;

import edu.colegiosprisma.school.validation.RangeAge;
import edu.colegiosprisma.school.util.Utils;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class RangeAgeValidator implements ConstraintValidator<RangeAge, Object> {
    private String fieldDate;
    private int min;
    private int max;

    @Override
    public void initialize(RangeAge constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.fieldDate = constraintAnnotation.fieldDate();
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        LocalDate localDate = (LocalDate) new BeanWrapperImpl(value).getPropertyValue(fieldDate);
        int edad = Utils.getAge(localDate);
        boolean isValid = localDate != null && edad < max && edad >= min;
        if (!isValid) {
            context.disableDefaultConstraintViolation(); // Permite agregar más mensajes de error a la validación de la constraint
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()) // Agrega el mensaje de error
                    .addPropertyNode(fieldDate) // Agrega el nodo del campo que no cumple la constraint
                    .addConstraintViolation(); // Agrega la constraint que no cumple el campo en particular
        }
        return isValid;
    }
}
