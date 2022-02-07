package edu.colegiosprisma.school.validation.validator;

import edu.colegiosprisma.school.entity.DocumentType;
import edu.colegiosprisma.school.validation.IdentityCard;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IndentityCardValidator implements ConstraintValidator<IdentityCard, Object> {
    private String fieldType;
    private String fieldLength;

    @Override
    public void initialize(IdentityCard constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        fieldType = constraintAnnotation.fieldType();
        fieldLength = constraintAnnotation.fieldLength();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        DocumentType documentType = (DocumentType) new BeanWrapperImpl(value).getPropertyValue(fieldType);
        String documentNumber = (String) new BeanWrapperImpl(value).getPropertyValue(fieldLength);
        boolean isValid = documentType.getLength() == documentNumber.length();
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(fieldLength)
                    .addConstraintViolation();
        }
        return isValid;
    }
}
