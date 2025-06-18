package de.upteams.sortmeister.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class NoCyrillicValidator implements ConstraintValidator<NoCyrillic, String> {

    private static final Pattern CYRILLIC_PATTERN = Pattern.compile("\\p{IsCyrillic}");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true;
        }
        return !CYRILLIC_PATTERN.matcher(value).find();
    }
}