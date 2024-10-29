package com.project.url_shortening_service.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HttpLinkValidator.class)
public @interface ValidHttpLink {
    String message() default "Invalid HTTP link";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
