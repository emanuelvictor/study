package br.org.pti.integrador.application.components.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 06/02/2020
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ConditionalValidations.class)
@Constraint(validatedBy = {ConditionalValidator.class})
public @interface ConditionalValidation {

    /**
     *
     * @return
     */
    String message() default "This field is required.";

    /**
     *
     * @return
     */
    Class<?>[] groups() default {};

    /**
     *
     * @return
     */
    Class<? extends Payload>[] payload() default {};

    /**
     *
     * @return
     */
    String selected();

    /**
     *
     * @return
     */
    String[] required();

    /**
     *
     * @return
     */
    String[] values();
}
