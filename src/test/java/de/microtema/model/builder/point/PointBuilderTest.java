package de.microtema.model.builder.point;

import de.microtema.model.builder.ModelBuilderFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PointBuilderTest {

    @Test
    public void min() {

        Point point = ModelBuilderFactory.min(Point.class);

        assertNotNull(point);
        assertNotNull(point.getX());
        assertNotNull(point.getY());
    }
}
