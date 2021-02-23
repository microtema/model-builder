package de.microtema.model.builder.util;

import org.junit.Test;

import javax.validation.constraints.NotNull;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class AnnotationUtilTest {

    AnnotationUtil sut;

    @NotNull
    String notNull;

    String str;

    @Test
    public void isAnyRequiredAnnotationOnNull() {

        boolean requiredAnnotation = AnnotationUtil.isAnyRequiredAnnotation(null);

        assertFalse(requiredAnnotation);
    }

    @Test
    public void addNotNullPredicate() {

        assertNotNull(AnnotationUtil.addPredicate("NotNull", it -> true));
    }

    @Test
    public void addNotEmptyPredicate() {

        assertNotNull(AnnotationUtil.addPredicate("NotEmpty", it -> true));
    }

    @Test
    public void addNotBlankPredicate() {

        assertNotNull(AnnotationUtil.addPredicate("NotBlank", it -> true));
    }
}
