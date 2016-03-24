package de.seven.fate.model.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public final class ClassUtil {

    private static final Logger LOGGER = Logger.getLogger(ClassUtil.class.getName());

    private ClassUtil() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }

    public static <T> T getGenericType(Class<?> classType) {

        ParameterizedType genericSuperclass = (ParameterizedType) classType.getGenericSuperclass();

        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();

        return (T) actualTypeArguments[0];
    }

    public static <T> T createInstance(Class<T> instanceType) {

        T instance = null;

        try {
            instance = instanceType.newInstance();
        } catch (Exception e) {
            LOGGER.warning("unable to create new instance of Type " + instanceType.getCanonicalName());
        }

        return instance;
    }


    public static List<String> getPropertyNames(Class<?> objectClass) {

        BeanInfo info;

        try {
            info = Introspector.getBeanInfo(objectClass);
        } catch (IntrospectionException e) {
            throw new IllegalArgumentException(e);
        }

        PropertyDescriptor[] propertyDescriptors = info.getPropertyDescriptors();

        List<String> list = new ArrayList<String>();

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (StringUtils.equals(propertyDescriptor.getName(), "class")) {
                continue;
            }
            list.add(propertyDescriptor.getName());
        }

        return list;
    }

    public static Class<?> getPropertyType(String propertyName, Class<?> objType) {

        Field[] allFields = getAllFields(objType);
        for (Field field : allFields) {
            if (field.getName().equals(propertyName)) {
                return field.getType();
            }
        }

        return null;
    }

    private static Field[] getAllFields(Class<?> type) {

        if (type.getSuperclass() != null) {
            return ArrayUtils.addAll(getAllFields(type.getSuperclass()), type.getDeclaredFields());
        }

        return type.getDeclaredFields();
    }
}