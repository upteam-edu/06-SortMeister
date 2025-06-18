package de.upteams.sortmeister.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoCyrillicValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoCyrillic {
    String message() default "Container name cannot contain Cyrillic characters. Please use Latin alphabet only.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
} 