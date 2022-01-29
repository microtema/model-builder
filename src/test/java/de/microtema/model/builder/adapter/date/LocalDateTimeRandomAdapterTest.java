package de.microtema.model.builder.adapter.date;

import de.microtema.model.builder.annotation.Inject;
import de.microtema.model.builder.util.FieldInjectionUtil;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class LocalDateTimeRandomAdapterTest {

    @Inject
    private LocalDateTimeRandomAdapter sut;

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void randomValueDefault() {
        LocalDateTime answer = sut.randomValueDefault("createTime");
        assertNotNull(answer);

        LocalDateTime other = sut.randomValueDefault("updateTime");

        assertNotEquals(answer, other);
    }

    @Test
    public void fixValue() {
        LocalDateTime answer = sut.fixValue("updateTime");
        assertNotNull(answer);

        LocalDateTime other = sut.fixValue("updateTime");

        assertEquals(answer, other);
    }
}
