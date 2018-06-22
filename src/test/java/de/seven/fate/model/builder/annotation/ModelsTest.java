package de.seven.fate.model.builder.annotation;

import de.seven.fate.model.builder.enums.ModelsType;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ModelsTest {

    private Models sut;

    @Models
    private List<String> field;

    @Test
    public void testAnnotationDefaultValues() throws Exception {

        Models annotation = getClass().getDeclaredField("field").getAnnotation(Models.class);

        assertNotNull(annotation);

        assertEquals(-1, annotation.size());
        assertEquals(ModelsType.LIST, annotation.type());
    }
}
