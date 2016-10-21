package de.seven.fate.model.app;

import de.seven.fate.model.builder.ModelBuilder;
import de.seven.fate.model.builder.ModelBuilderFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AppBuilderTest {

    @Test
    public void name() throws Exception {
        ModelBuilder<App> builder = ModelBuilderFactory.createBuilder(App.class);

        App app = builder.min();

        assertNotNull(app);
        assertNotNull(app.getName());

        assertNotNull(app.getApps());
        assertNotNull(app.getSets());
    }
}
