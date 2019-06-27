package de.seven.fate.model.builder.annotation;

import de.seven.fate.model.builder.enums.ModelType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to indicate an model point of a bean into a test classes.
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Models {

    int size() default -1;

    ModelType type() default ModelType.MIX;
}
