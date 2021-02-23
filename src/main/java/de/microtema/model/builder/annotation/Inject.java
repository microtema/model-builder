package de.microtema.model.builder.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Used to indicate an injection point of a bean into a test classes.
 */
@Target({FIELD})
@Retention(RUNTIME)
@Documented
public @interface Inject {
}