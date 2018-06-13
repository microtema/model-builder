package de.seven.fate.model.builder.geo;

import de.seven.fate.model.builder.ModelBuilder;
import de.seven.fate.model.builder.ModelBuilderFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GeoDataBuilderTest {

    ModelBuilder<GeoData> sut = new GeoDataBuilder();

    @Test
    public void shouldGenerateMin() {

        GeoData min = ModelBuilderFactory.createBuilder(GeoData.class).min();

        assertNotNull(min);

        assertNotNull(min.getLatitude());
        assertNotNull(min.getLongitude());
        assertNotNull(min.getType());
    }

    @Test
    public void min() {

        GeoData min = sut.min();

        assertNotNull(min);

        assertNotNull(min.getLatitude());
        assertNotNull(min.getLongitude());
        assertNotNull(min.getType());
    }
}
