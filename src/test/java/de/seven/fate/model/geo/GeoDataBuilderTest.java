package de.seven.fate.model.geo;

import de.seven.fate.model.builder.ModelBuilder;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Mario on 26.03.2016.
 */
public class GeoDataBuilderTest {

    ModelBuilder<GeoData> sut = new GeoDataBuilder();

    @Test
    public void min() {

        GeoData min = sut.min();

        assertNotNull(min);

        assertNotNull(min.getLatitude());
        assertNotNull(min.getLongitude());
        assertNotNull(min.getType());
    }
}
