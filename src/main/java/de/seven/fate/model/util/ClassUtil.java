package de.seven.fate.model.util;

import org.apache.commons.lang3.ClassUtils;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings({"ALL", "unchecked"})
public final class ClassUtil {

    private static final Logger LOGGER = Logger.getLogger(ClassUtil.class.getName());

    private ClassUtil() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }

    public static <T> T getGenericType(Class<?> type) {
        assert type != null;

        ParameterizedType genericSuperclass = (ParameterizedType) type.getGenericSuperclass();

        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();

        return (T) actualTypeArguments[0];
    }

    public static <T> Class<T> getActualTypeArgument(Type genericType) {
        assert genericType != null;

        if (genericType instanceof ParameterizedType) {

            ParameterizedType parameterizedType = (ParameterizedType) genericType;

            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Type actualTypeArgument = actualTypeArguments[0];

            return (Class<T>) actualTypeArgument;
        }

        return null;
    }

    public static <T> T createInstance(Class<T> instanceType) {
        assert instanceType != null;

        try {
            return instanceType.newInstance();
        } catch (Exception e) {

            LOGGER.log(Level.SEVERE, "unable to create new instance of Type " + instanceType, e);
        }

        throw new IllegalArgumentException("unable to create new instance of Type " + instanceType);
    }


    public static List<String> getPropertyNames(Class<?> type) {
        assert type != null;

        BeanInfo info;

        try {
            info = Introspector.getBeanInfo(type);
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

    public static Class<?> getPropertyType(String propertyName, Class<?> type) {
        assert propertyName != null;
        assert type != null;

        List<Field> allFields = getAllFields(type);

        for (Field field : allFields) {

            if (field.getName().equals(propertyName)) {
                return field.getType();
            }
        }

        return null;
    }

    public static List<Field> getAllFields(Class<?> type) {
        assert type != null;

        if (type.getSuperclass() != null) {

            List<Field> fields = getAllFields(type.getSuperclass());

            fields.addAll(CollectionUtil.asList(type.getDeclaredFields()));

            return fields;
        }

        return CollectionUtil.asList(type.getDeclaredFields());
    }

    public static boolean isComplexType(Class<?> type) {

        return !type.isPrimitive() && type != String.class && !ClassUtils.isPrimitiveOrWrapper(type) && type != Date.class && type != BigDecimal.class;
    }

    public static <T> boolean isCollectionType(Class<T> type) {
        return Collection.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type);
    }

    public static boolean isFieldRequired(Field field) {
        assert field != null;

        NotNull annotation = field.getAnnotation(NotNull.class);

        if (annotation != null) {
            return true;
        }

        XmlAttribute xmlAttribute = field.getAnnotation(XmlAttribute.class);

        if (xmlAttribute != null) {
            return xmlAttribute.required();
        }

        XmlElement xmlElement = field.getAnnotation(XmlElement.class);

        if (xmlElement != null) {
            return xmlAttribute.required();
        }

        return false;
    }
}
