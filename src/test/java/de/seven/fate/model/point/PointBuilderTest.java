package de.seven.fate.model.point;

import de.seven.fate.model.builder.ModelBuilderFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PointBuilderTest {

    @Test
    public void min() throws Exception {

        Point point = ModelBuilderFactory.min(Point.class);

        assertNotNull(point);
        assertNotNull(point.getX());
        assertNotNull(point.getY());
    }
}
