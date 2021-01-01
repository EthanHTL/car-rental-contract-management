package com.example.carrentalcontract.annotation;

import java.lang.annotation.*;

@Repeatable(NotNulls.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {
    String[] value() default "";

    String Field() default "";

    String status() default "200";

    String index() default "0";

    String message() default "not null";
}
