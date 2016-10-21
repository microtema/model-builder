package de.seven.fate.model.order;

import de.seven.fate.model.builder.ModelBuilder;
import de.seven.fate.model.builder.ModelBuilderFactory;
import org.junit.Assert;
import org.junit.Test;

public class PurchaseOrderBuilderTest {

    @Test
    public void name() throws Exception {

        ModelBuilder<PurchaseOrder> builder = ModelBuilderFactory.createBuilder(PurchaseOrder.class);

        PurchaseOrder min = builder.min();

        Assert.assertNotNull(min);
        Assert.assertNotNull(min.getAddress());
        Assert.assertNotNull(min.getPerson());
        Assert.assertNotNull(min.getOrderItems());
        Assert.assertNotNull(min.getOrderItemSet());
    }
}
