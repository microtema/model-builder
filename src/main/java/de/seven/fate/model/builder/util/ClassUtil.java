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

    private static final Map<Class<?>, Class<?>> primitiveWrapperMap = new HashMap<>();
    private static final Map<Class<?>, Class<?>> wrapperPrimitiveMap = new HashMap<>();

    static {
        primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
        primitiveWrapperMap.put(Byte.TYPE, Byte.class);
        primitiveWrapperMap.put(Character.TYPE, Character.class);
        primitiveWrapperMap.put(Short.TYPE, Short.class);
        primitiveWrapperMap.put(Integer.TYPE, Integer.class);
        primitiveWrapperMap.put(Long.TYPE, Long.class);
        primitiveWrapperMap.put(Double.TYPE, Double.class);
        primitiveWrapperMap.put(Float.TYPE, Float.class);
        primitiveWrapperMap.put(Void.TYPE, Void.TYPE);
    }

    static {
        for (Map.Entry<Class<?>, Class<?>> entry : primitiveWrapperMap.entrySet()) {

            Class<?> primitiveClass = entry.getKey();

            Class<?> wrapperClass = entry.getValue();

            if (!primitiveClass.equals(wrapperClass)) {
                wrapperPrimitiveMap.put(wrapperClass, primitiveClass);
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
        return wrapperPrimitiveMap.containsKey(type);
    }

    private static boolean isDateType(Class<?> type) {

        return type == Date.class || type == LocalDateTime.class || type == LocalDate.class;
    }

}
