package com.example.rest.validation;

import java.util.regex.Pattern;

import com.example.rest.annotation.Phone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidation implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone annotation) {
        ConstraintValidator.super.initialize(annotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Pattern.compile("^\\d{10}$").matcher(value).matches();
    }
}
