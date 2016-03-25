package de.seven.fate.model.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@SuppressWarnings({"ALL", "unchecked"})
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

        List<String> list = new ArrayList<>();

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {

            if ("class".equals(propertyDescriptor.getName())) {
                continue;
            }

            list.add(propertyDescriptor.getName());
        }

        return list;
    }

    public static Class<?> getPropertyType(String propertyName, Class<?> objType) {

        List<Field> allFields = getAllFields(objType);

        for (Field field : allFields) {

            if (field.getName().equals(propertyName)) {
                return field.getType();
            }
        }

        return null;
    }

    private static List<Field> getAllFields(Class<?> type) {

        if (type.getSuperclass() != null) {

            List<Field> fields = getAllFields(type.getSuperclass());

            fields.addAll(CollectionUtil.asList(type.getDeclaredFields()));

            return fields;
        }

        return CollectionUtil.asList(type.getDeclaredFields());
    }
}