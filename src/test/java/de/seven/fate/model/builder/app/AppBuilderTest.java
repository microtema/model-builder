package de.seven.fate.model.builder.app;

import de.seven.fate.model.builder.ModelBuilder;
import de.seven.fate.model.builder.ModelBuilderFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AppBuilderTest {

    @Test
    public void shouldPreventStackOverflowError() {

        ModelBuilder<App> builder = ModelBuilderFactory.createBuilder(App.class);

        App app = builder.min();

        assertNotNull(app);
        assertNotNull(app.getName());
        assertNull(app.getTitle());

        assertNotNull(app.getApps());
        assertNotNull(app.getSets());
        assertNotNull(app.getLocalDateTime());

    }
}
