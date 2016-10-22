package de.seven.fate.model.adapter;

import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

@Ignore
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

}
