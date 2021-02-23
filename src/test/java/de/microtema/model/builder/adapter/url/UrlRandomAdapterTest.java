package de.microtema.model.builder.adapter.url;

import de.microtema.model.builder.adapter.string.LinkPropertyRandomAdapter;
import de.microtema.model.builder.annotation.Inject;
import de.microtema.model.builder.util.FieldInjectionUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertNotNull;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LinkPropertyRandomAdapter.class)
public class UrlRandomAdapterTest {

    @Inject
    private UrlRandomAdapter sut;

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void randomValueDefault() {

        URL url = sut.randomValueDefault(null);

        assertNotNull(url);

        assertNotNull(url.getFile());
        assertNotNull(url.getHost());
        assertNotNull(url.getPath());
    }
}