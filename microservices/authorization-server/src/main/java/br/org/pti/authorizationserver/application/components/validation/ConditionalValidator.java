package br.org.pti.authorizationserver.application.components.validation;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static org.springframework.util.StringUtils.isEmpty;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 06/02/2020
 */
@Slf4j
public class ConditionalValidator implements ConstraintValidator<ConditionalValidation, Object> {

    private ConditionalValidation annotation;

    /**
     * {@inheritDoc}
     *
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        final String selected = this.annotation.selected();
        final String[] required = this.annotation.required();
        final String message = this.annotation.message();
        final String[] values = this.annotation.values();

        boolean valid = true;

        try {
            final Object actualValue = BeanUtils.getProperty(value, selected);

            if (Arrays.asList(values).contains(actualValue)) {
                for (String property : required) {
                    final Object requiredValue = BeanUtils.getProperty(value, property);
                    valid = requiredValue != null && !isEmpty(requiredValue);
                    if (!valid) {
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate(message)
                                .addPropertyNode(property)
                                .addConstraintViolation();
                    }
                }
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            log.error("Can't apply conditional validation to the field " + selected + " an error has occurred", ex);
            return false;
        }

        return valid;
    }

    /**
     * {@inheritDoc}
     *
     * @param conditionalValidation
     */
    @Override
    public void initialize(ConditionalValidation conditionalValidation) {
        this.annotation = conditionalValidation;
    }
}
