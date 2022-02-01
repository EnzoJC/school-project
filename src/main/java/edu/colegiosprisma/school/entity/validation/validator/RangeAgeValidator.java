package edu.colegiosprisma.school.entity.validation.validator;

import edu.colegiosprisma.school.entity.validation.RangeAge;
import edu.colegiosprisma.school.util.Utils;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class RangeAgeValidator implements ConstraintValidator<RangeAge, Object> {
    private String field;
    private int min;
    private int max;

    @Override
    public void initialize(RangeAge constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.field = constraintAnnotation.field();
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        LocalDate localDate = (LocalDate) new BeanWrapperImpl(value).getPropertyValue(field);
        int edad = Utils.getAge(localDate);
        return localDate != null && edad < max && edad >= min;
    }
}
