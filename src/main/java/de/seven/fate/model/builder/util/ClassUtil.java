package de.seven.fate.model.builder.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"ALL", "unchecked"})
public final class ClassUtil {

    private static final Map<Class<?>, Class<?>> CLASS_CLASS_HASH_MAP = new HashMap<>();
    private static final Map<Class<?>, Class<?>> WRAPPER_PRIMITIVE_MAP = new HashMap<>();

    static {
        CLASS_CLASS_HASH_MAP.put(Boolean.TYPE, Boolean.class);
        CLASS_CLASS_HASH_MAP.put(Byte.TYPE, Byte.class);
        CLASS_CLASS_HASH_MAP.put(Character.TYPE, Character.class);
        CLASS_CLASS_HASH_MAP.put(Short.TYPE, Short.class);
        CLASS_CLASS_HASH_MAP.put(Integer.TYPE, Integer.class);
        CLASS_CLASS_HASH_MAP.put(Long.TYPE, Long.class);
        CLASS_CLASS_HASH_MAP.put(Double.TYPE, Double.class);
        CLASS_CLASS_HASH_MAP.put(Float.TYPE, Float.class);
        CLASS_CLASS_HASH_MAP.put(Void.TYPE, Void.TYPE);
    }

    static {
        for (Map.Entry<Class<?>, Class<?>> entry : CLASS_CLASS_HASH_MAP.entrySet()) {

            Class<?> primitiveClass = entry.getKey();

            Class<?> wrapperClass = entry.getValue();

            if (!primitiveClass.equals(wrapperClass)) {
                WRAPPER_PRIMITIVE_MAP.put(wrapperClass, primitiveClass);
            }
        }
    }

    private ClassUtil() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }

    public static boolean isComplexType(Class<?> type) {

        return !type.isPrimitive() && !isPrimitiveOrWrapper(type) && !isDateType(type) && !isStringOrBigDecimalType(type);
    }

    private static boolean isStringOrBigDecimalType(Class<?> type) {

        return type == String.class || type == BigDecimal.class;
    }

    public static <T> boolean isCollectionType(Class<T> type) {
        return Collection.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type);
    }

    public static boolean isPrimitiveOrWrapper(Class<?> type) {
        if (type == null) {
            return false;
        }

        return type.isPrimitive() || isPrimitiveWrapper(type);
    }

    public static boolean isPrimitiveWrapper(Class<?> type) {
        return WRAPPER_PRIMITIVE_MAP.containsKey(type);
    }

    private static boolean isDateType(Class<?> type) {

        return type == Date.class || type == LocalDateTime.class || type == LocalDate.class;
    }

}
