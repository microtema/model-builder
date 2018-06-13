package de.seven.fate.model.builder.order;

import de.seven.fate.model.builder.ModelBuilder;
import de.seven.fate.model.builder.ModelBuilderFactory;
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
