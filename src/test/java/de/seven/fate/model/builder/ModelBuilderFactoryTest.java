package de.seven.fate.model.builder;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class ModelBuilderFactoryTest {

    ModelBuilderFactory sut;

    @Test
    public void createPurchaseItemBuilder() {

        /*
        ModelBuilder<PurchaseItem> builder = ModelBuilderFactory.createBuilder(PurchaseItem.class);

        PurchaseItem purchaseItem = builder.min();

        assertNotNull(purchaseItem);
        assertNotNull(purchaseItem.getGiftWrap());
        assertNotNull(purchaseItem.getPartNumber());
        assertNotNull(purchaseItem.getPrice());
        assertNotNull(purchaseItem.getProductName());
        assertNotNull(purchaseItem.getQuantity());

        */
    }

    @Test
    public void createPurchaseOrderBuilder() {

        /**
        ModelBuilder<PurchaseOrder> builder = ModelBuilderFactory.createBuilder(PurchaseOrder.class);

        PurchaseOrder purchaseOrder = builder.min();

        assertNotNull(purchaseOrder);
        assertNotNull(purchaseOrder.getAddress());
        assertNotNull(purchaseOrder.getPerson());
        assertNotNull(purchaseOrder.getOrderItems());

         */
    }

}
