package de.microtema.model.builder.order;

import de.microtema.model.builder.ModelBuilder;
import de.microtema.model.builder.ModelBuilderFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PurchaseItemBuilderTest {

    ModelBuilder<PurchaseItem> sut = ModelBuilderFactory.createBuilder(PurchaseItem.class);

    @Test
    public void min() {

        PurchaseItem min = sut.max();

        assertNotNull(min);
        assertNotNull(min.getGiftWrap());
        assertNotNull(min.getPartNumber());
        assertNotNull(min.getProductName());
        assertNotNull(min.getQuantity());
        assertNotNull(min.getLocalDateTime());
    }
}
