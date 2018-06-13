package de.seven.fate.model.builder.adapter;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertNotNull;


public class TypeRandomAdapterFactoryTest {


    TypeRandomAdapterFactory sut;

    @Test(expected = AssertionError.class)
    public void lookupAdapterAdapterThrowAssertionErrorOnNull() {
        TypeRandomAdapterFactory.lookupAdapter(null);
    }

    @Test
    public void lookupIntegerAdapter() {
        assertNotNull(TypeRandomAdapterFactory.lookupAdapter(Integer.class));
    }

    @Test
    public void lookupBooleanAdapter() {
        assertNotNull(TypeRandomAdapterFactory.lookupAdapter(Boolean.class));
    }

    @Test
    public void lookupStringAdapter() {
        assertNotNull(TypeRandomAdapterFactory.lookupAdapter(String.class));
    }

    @Test
    public void lookupLongAdapter() {
        assertNotNull(TypeRandomAdapterFactory.lookupAdapter(Long.class));
    }

    @Test
    public void lookupDoubleAdapter() {
        assertNotNull(TypeRandomAdapterFactory.lookupAdapter(Double.class));
    }

    @Test
    public void lookupBigDecimalAdapter() {
        assertNotNull(TypeRandomAdapterFactory.lookupAdapter(BigDecimal.class));
    }

    @Test
    public void lookupDateAdapter() {
        assertNotNull(TypeRandomAdapterFactory.lookupAdapter(Date.class));
    }

    @Test
    public void lookupMapAdapter() {
        assertNotNull(TypeRandomAdapterFactory.lookupAdapter(Map.class));
    }

}
