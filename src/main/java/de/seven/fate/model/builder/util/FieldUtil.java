package de.seven.fate.model.builder.util;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Field Util
 */
public final class FieldUtil {

    private static final Set<String> IGNORE_PROPERTIES = Stream.of("class").collect(Collectors.toSet());

    private static final Field[] NO_FIELDS = {};
    /**
     * Cache for {@link Class#getDeclaredFields()}, allowing for fast iteration.
     */
    private static final Map<Class<?>, Field[]> DECLARED_FIELDS_CACHE = new ConcurrentHashMap<>(256);

    private FieldUtil() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }

    /**
     * @param type may not be null
     * @return List of Field-properties
     */
    public static List<Field> getPropertyFields(Class<?> type) {
        Validate.notNull(type);

        List<Field> fields = getAllFieldsImpl(type);

        BeanInfo beanInfo = getBeanInfo(type);

        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();

        List<String> propertyDescriptors = Stream.of(descriptors).filter(FieldUtil::isValid).map(PropertyDescriptor::getName).collect(Collectors.toList());

        return fields.stream().filter(it -> propertyDescriptors.contains(it.getName())).collect(Collectors.toList());
    }

    /**
     * Return true if is Required Field else false
     *
     * @param field may not be null
     * @return boolean
     */
    public static boolean isRequiredField(Field field) {
        Validate.notNull(field);

        Annotation[] annotations = field.getAnnotations();

        return AnnotationUtil.isAnyRequiredAnnotation(annotations);
    }

    /**
     * get Filed value or throw IllegalArgumentException for given field
     *
     * @param field  may not be null
     * @param object may not be null
     * @param <T>    required type
     * @return Object or throw IllegalArgumentException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Field field, Object object) {
        Validate.notNull(field);
        Validate.notNull(object);

        try {
            Object value = field.get(object);

            return (T) value;
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Unable to get value from field: " + field, e);
        }
    }

    /**
     * Set the field represented by the supplied {@link Field field object} on the
     * specified {@link Object target object} to the specified {@code value}.
     * In accordance with {@link Field#set(Object, Object)} semantics, the new value
     * is automatically unwrapped if the underlying field has a primitive type.
     * <p>Thrown exceptions are handled via a call to {@link IllegalStateException}.
     *
     * @param field  the field to set
     * @param target the target object on which to set the field
     * @param value  the value to set (may be {@code null})
     */
    public static void setFieldValue(Field field, Object target, Object value) {

        try {

            FieldUtils.writeField(field, target, value, true);

        } catch (IllegalAccessException ex) {
            throw new IllegalStateException("Unexpected reflection exception - " + ex.getClass().getName() + ": " + ex.getMessage());
        }
    }

    /**
     * Invoke the given callback on all fields in the target class, going up the
     * class hierarchy to get all declared fields.
     *
     * @param clazz the target class to analyze
     * @param fc    the callback to invoke for each field
     * @param ff    the filter that determines the fields to apply the callback to
     */
    public static void doWithFields(Class<?> clazz, FieldCallback fc, FieldFilter ff) {
        // Keep backing up the inheritance hierarchy.
        Class<?> targetClass = clazz;
        do {

            Field[] fields = getDeclaredFields(targetClass);
            for (Field field : fields) {
                if (ff != null && !ff.matches(field)) {
                    continue;
                }
                try {
                    fc.doWith(field);
                } catch (IllegalAccessException ex) {
                    throw new IllegalStateException("Not allowed to access field '" + field.getName() + "': " + ex);
                }
            }
            targetClass = targetClass.getSuperclass();
        }
        while (targetClass != null && targetClass != Object.class);
    }

    private static boolean isValid(PropertyDescriptor descriptor) {
        assert descriptor != null;

        return !IGNORE_PROPERTIES.contains(descriptor.getName());
    }

    private static BeanInfo getBeanInfo(Class beanType) {
        try {
            return Introspector.getBeanInfo(beanType);
        } catch (IntrospectionException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static List<Field> getAllFieldsImpl(Class<?> type) {
        assert type != null;

        if (type.getSuperclass() != null) {

            List<Field> fields = getPropertyFields(type.getSuperclass());

            fields.addAll(filterFields(type.getDeclaredFields()));

            return fields;
        }

        return filterFields(type.getDeclaredFields());
    }

    /**
     * This variant retrieves {@link Class#getDeclaredFields()} from a local cache
     * in order to avoid the JVM's SecurityManager check and defensive array copying.
     *
     * @param clazz the class to introspect
     * @return the cached array of fields
     * @see Class#getDeclaredFields()
     */
    private static Field[] getDeclaredFields(Class<?> clazz) {
        Field[] result = DECLARED_FIELDS_CACHE.get(clazz);
        if (result == null) {
            result = clazz.getDeclaredFields();
            DECLARED_FIELDS_CACHE.put(clazz, result.length == 0 ? NO_FIELDS : result);
        }
        return result;
    }

    private static List<Field> filterFields(Field[] fields) {

        return Stream.of(fields)
                .filter(it -> !IGNORE_PROPERTIES.contains(it.getName()))
                .filter(it -> !it.getName().startsWith("$"))
                .collect(Collectors.toList());
    }

    /**
     * Callback interface invoked on each field in the hierarchy.
     */
    @FunctionalInterface
    public interface FieldCallback {

        /**
         * Perform an operation using the given field.
         *
         * @param field the field to operate on
         *              throws IllegalAccessException
         * @throws IllegalAccessException exception
         */
        void doWith(Field field) throws IllegalAccessException;
    }

    /**
     * Callback optionally used to filter fields to be operated on by a field callback.
     */
    @FunctionalInterface
    public interface FieldFilter {

        /**
         * Determine whether the given field matches.
         *
         * @param field the field to check
         * @return boolean
         */
        boolean matches(Field field);
    }
}
