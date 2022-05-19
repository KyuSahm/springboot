package com.example.validation.validator;

import com.example.validation.annotation.YearMonth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {
    private String pattern;

    @Override
    public void initialize(YearMonth constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        // 구현 사항
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 구현 사항
        String tempDate = value + "01";
        try {
            LocalDate localDate = LocalDate.parse(tempDate, DateTimeFormatter.ofPattern(this.pattern + "dd"));
            return true;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }
}
