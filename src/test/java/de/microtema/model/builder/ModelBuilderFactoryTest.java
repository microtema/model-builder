package de.microtema.model.builder;

import de.microtema.model.builder.address.Address;
import de.microtema.model.builder.order.PurchaseItem;
import de.microtema.model.builder.order.PurchaseOrder;
import de.microtema.model.builder.person.Person;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class ModelBuilderFactoryTest {

    ModelBuilderFactory sut;

    @Test(expected = UnsupportedOperationException.class)
    public void utilityClassTest() throws Exception {

        Constructor<ModelBuilderFactory> constructor = ModelBuilderFactory.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
        } catch (InvocationTargetException e) {
            throw (UnsupportedOperationException) e.getTargetException();
        }
    }

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

    @Test
    public void fromResourceWithinJson() {

        Class<Address> modelType = Address.class;

        Address answer = ModelBuilderFactory.createBuilder(modelType).fromResource("address.json");

        assertNotNull(answer);
    }

    @Test
    public void listFromResource() {

        Class<Address> modelType = Address.class;

        List<Address> answer = ModelBuilderFactory.createBuilder(modelType).listFromResource("addresses.json");

        assertNotNull(answer);
        assertFalse(answer.isEmpty());
    }

    @Test
    public void setFromResource() {

        Class<Address> modelType = Address.class;

        Set<Address> answer = ModelBuilderFactory.createBuilder(modelType).setFromResource("addresses.json");

        assertNotNull(answer);
        assertFalse(answer.isEmpty());
    }

    @Test
    public void min() {

        Class<Person> modelType = Person.class;

        Person model = ModelBuilderFactory.min(modelType);

        assertNotNull(model);
    }

    @Test
    public void max() {

        Class<Person> modelType = Person.class;

        Person model = ModelBuilderFactory.max(modelType);

        assertNotNull(model);
    }

    @Test
    public void mix() {

        Class<Person> modelType = Person.class;

        Person model = ModelBuilderFactory.mix(modelType);

        assertNotNull(model);
    }

    @Test
    public void list() {

        Class<Person> modelType = Person.class;

        List<Person> models = ModelBuilderFactory.list(modelType);

        assertNotNull(models);

        assertFalse(models.isEmpty());
    }

    @Test
    public void listWithSize() {

        Class<Person> modelType = Person.class;

        List<Person> models = ModelBuilderFactory.list(modelType, 1);

        assertNotNull(models);

        assertEquals(1, models.size());
    }


    @Test
    public void set() {

        Class<Person> modelType = Person.class;

        Set<Person> models = ModelBuilderFactory.set(modelType);

        assertNotNull(models);

        assertFalse(models.isEmpty());
    }

    @Test
    public void setWithSize() {

        Class<Person> modelType = Person.class;

        Set<Person> models = ModelBuilderFactory.set(modelType, 1);

        assertNotNull(models);

        assertEquals(1, models.size());
    }

    @Test
    public void fromPropertiesResourceWithFactory() {

        Class<Properties> modelType = Properties.class;

        Properties properties = ModelBuilderFactory.fromResource(modelType, "messages.properties");

        assertFalse(properties.isEmpty());
        assertEquals("properties", properties.get("key"));
    }

}
