package de.seven.fate.model.builder.annotation;

import de.seven.fate.model.builder.enums.ModelType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Model {
    ModelType type() default ModelType.MIN;

    String resource() default "";
}
