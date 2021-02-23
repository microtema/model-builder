package de.microtema.model.builder.annotation;

import de.microtema.model.builder.enums.ModelType;

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
public @interface Model {

    ModelType type() default ModelType.MIN;

    String resource() default "";
}
