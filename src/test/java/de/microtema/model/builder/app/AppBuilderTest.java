package de.microtema.model.builder.app;

import org.junit.Test;

import static de.microtema.model.builder.ModelBuilderFactory.min;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AppBuilderTest {

    App sut = min(App.class);

    @Test
    public void shouldPreventStackOverflowError() {

        assertNotNull(sut);
        assertNotNull(sut.getName());

        App parentApp = sut.getParent();
        assertNotNull(parentApp);
        assertNull(parentApp.getParent());

        assertNotNull(sut.getApps());
        assertNotNull(sut.getChildren());
    }
}
