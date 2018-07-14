package de.seven.fate.model.builder;

import de.seven.fate.model.builder.order.PurchaseItem;
import de.seven.fate.model.builder.order.PurchaseOrder;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class ModelBuilderFactoryTest {

    ModelBuilderFactory sut;

    @Test
    public void createPurchaseItemBuilder() {

        ModelBuilder<PurchaseItem> builder = ModelBuilderFactory.createBuilder(PurchaseItem.class);

        PurchaseItem purchaseItem = builder.max();

        assertNotNull(purchaseItem);
        assertNotNull(purchaseItem.getId());
        assertNotNull(purchaseItem.getGiftWrap());
        assertNotNull(purchaseItem.getPartNumber());
        assertNotNull(purchaseItem.getPrice());
        assertNotNull(purchaseItem.getProductName());
        assertNotNull(purchaseItem.getQuantity());
        assertNotNull(purchaseItem.getDescriptions());
        assertTrue(purchaseItem.getRate() != 0);
    }

    @Test
    public void createPurchaseOrderBuilder() {

        ModelBuilder<PurchaseOrder> builder = ModelBuilderFactory.createBuilder(PurchaseOrder.class);

        PurchaseOrder purchaseOrder = builder.min();

        assertNotNull(purchaseOrder);
        assertNotNull(purchaseOrder.getAddress());
        assertNotNull(purchaseOrder.getPerson());
        assertNotNull(purchaseOrder.getOrderItems());
    }

    @Test
    public void createBuilderInt() {
        Class<Integer> modelType = int.class;

        assertTrue(ModelBuilderFactory.createBuilder(modelType).min() != 0);
        assertFalse(ModelBuilderFactory.createBuilder(modelType).list().isEmpty());
        assertTrue(ModelBuilderFactory.mix(modelType) != 0);
    }

    @Test
    public void createBuilderLong() {
        Class<Long> modelType = long.class;

        assertTrue(ModelBuilderFactory.createBuilder(modelType).min() != 0);
        assertFalse(ModelBuilderFactory.createBuilder(modelType).list().isEmpty());
        assertTrue(ModelBuilderFactory.mix(modelType) != 0);
    }

    @Test
    public void createBuilderDouble() {
        Class<Double> modelType = double.class;

        assertTrue(ModelBuilderFactory.createBuilder(modelType).min() != 0);
        assertFalse(ModelBuilderFactory.createBuilder(modelType).list().isEmpty());
        assertTrue(ModelBuilderFactory.mix(modelType) != 0);
    }

    @Test
    public void createBuilderFloat() {
        Class<Float> modelType = float.class;

        assertTrue(ModelBuilderFactory.createBuilder(modelType).min() != 0);
        assertFalse(ModelBuilderFactory.createBuilder(modelType).list().isEmpty());
        assertTrue(ModelBuilderFactory.mix(modelType) != 0);
    }

    @Test
    public void createBuilderBoolean() {
        Class<Boolean> modelType = boolean.class;

        assertFalse(ModelBuilderFactory.createBuilder(modelType).list().isEmpty());
    }

    @Test
    public void createBinaryBuilder() {

        Class<Byte> modelType = byte.class;

        List<Byte> list = ModelBuilderFactory.createBuilder(modelType).list();

        assertFalse(list.isEmpty());

        list.forEach(Assert::assertNotNull);
    }

    @Test
    public void createMapBuilder() {

        Class<Map> modelType = Map.class;

        List<Map> list = ModelBuilderFactory.createBuilder(modelType).list();

        assertFalse(list.isEmpty());

        list.forEach(Assert::assertNotNull);
    }

    @Test
    public void fromPropertiesResource() {

        Class<Properties> modelType = Properties.class;

        Properties properties = ModelBuilderFactory.createBuilder(modelType).fromResource("messages.properties");

        assertFalse(properties.isEmpty());
        assertEquals("properties", properties.get("key"));
    }

    @Test
    public void fromXmlResource() {

        Class<Properties> modelType = Properties.class;

        Properties properties = ModelBuilderFactory.createBuilder(modelType).fromResource("messages.xml");

        assertFalse(properties.isEmpty());
        assertEquals("xml", properties.get("key"));
    }

}
