package de.seven.fate.model.builder.adapter.url;

import de.seven.fate.model.builder.adapter.string.LinkPropertyRandomAdapter;
import de.seven.fate.model.builder.annotation.Inject;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

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

    @Test(expected = IllegalArgumentException.class)
    public void name() {

        PowerMockito.mockStatic(LinkPropertyRandomAdapter.class);

        sut.randomValueDefault(null);
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