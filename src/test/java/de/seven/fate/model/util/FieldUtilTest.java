package de.seven.fate.model.util;

import de.seven.fate.model.address.Address;
import de.seven.fate.model.point.Point;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

public class FieldUtilTest {

    FieldUtil sut;

    @Test
    public void getAllFields() {

        List<Field> allFields = FieldUtil.getAllFields(Point.class);

        for (Field field : allFields){
            System.out.println("#########################################################################");
            System.out.println(field.getName());
            System.out.println("#########################################################################");
        }

        Assert.assertEquals(2, allFields.size());
        Assert.assertEquals("x", allFields.get(0).getName());
        Assert.assertEquals("y", allFields.get(1).getName());
    }


    @Test
    public void isRequiredField() throws Exception {
        List<Field> allFields = FieldUtil.getAllFields(Point.class);

        boolean requiredField = FieldUtil.isRequiredField(allFields.get(0));

        Assert.assertFalse(requiredField);
    }

    @Test
    public void addressIsRequiredField() throws Exception {

        Field streetName = Address.class.getDeclaredField("streetName");

        boolean requiredField = FieldUtil.isRequiredField(streetName);

        Assert.assertTrue(requiredField);
    }

}
