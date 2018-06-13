package de.seven.fate.model.builder.order;

import de.seven.fate.model.builder.ModelBuilder;
import de.seven.fate.model.builder.ModelBuilderFactory;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class PurchaseOrderBuilderTest {

    @Test
    public void min() {

        ModelBuilder<PurchaseOrder> builder = ModelBuilderFactory.createBuilder(PurchaseOrder.class);

        PurchaseOrder min = builder.min();

        assertNotNull(min);
        assertNotNull(min.getAddress());
        assertNotNull(min.getPerson());
        assertNotNull(min.getOrderItems());
        assertNull(min.getOrderItemSet());
    }

    @Test
    public void max() {

        ModelBuilder<PurchaseOrder> builder = ModelBuilderFactory.createBuilder(PurchaseOrder.class);

        PurchaseOrder min = builder.max();

        assertNotNull(min);
        assertNotNull(min.getAddress());
        assertNotNull(min.getPerson());
        assertNotNull(min.getOrderItems());
        assertNotNull(min.getOrderItemSet());
    }

    @Test
    public void list() {

        List<PurchaseOrder> min = ModelBuilderFactory.list(PurchaseOrder.class);

        assertNotNull(min);
        assertFalse(min.isEmpty());
    }

    @Test
    public void set() {

        Set<PurchaseOrder> min = ModelBuilderFactory.set(PurchaseOrder.class);

        assertNotNull(min);
        assertFalse(min.isEmpty());
    }
}
