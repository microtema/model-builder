package de.seven.fate.model.builder.annotation;

import de.seven.fate.model.builder.enums.ModelType;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ModelTest {

    private Model sut;

    @Model
    private String field;

    @Test
    public void testAnnotationDefaultValues() throws Exception {

        Model annotation = getClass().getDeclaredField("field").getAnnotation(Model.class);

        assertNotNull(annotation);

        assertEquals(StringUtils.EMPTY, annotation.resource());
        assertEquals(ModelType.MIN, annotation.type());
    }
}
