package com.example.carrentalcontract.annotation;

import java.lang.annotation.*;

@Repeatable(NotNulls.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {
    String[] value() default "";

    // 字段
    String field() default "";

    String name() default "";

    int statusCode() default 200;

    int argIndex() default 0;


}
