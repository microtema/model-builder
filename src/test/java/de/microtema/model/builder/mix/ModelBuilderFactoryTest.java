package de.microtema.model.builder.mix;

import de.microtema.model.builder.ModelBuilderFactory;
import org.junit.Assert;
import org.junit.Test;

public class ModelBuilderFactoryTest {

    ModelBuilderFactory sut;

    @Test
    public void max() {
        MixObject model = ModelBuilderFactory.max(MixObject.class);

        Assert.assertNotNull(model);
        Assert.assertNotNull(model.getAge());
        Assert.assertNotNull(model.getEnumType());
        Assert.assertNotNull(model.getList());
        Assert.assertNotNull(model.getMap());
        Assert.assertNotNull(model.getType());
    }
}
