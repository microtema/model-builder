package de.microtema.model.builder.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.apache.commons.lang3.Validate.notNull;

@SuppressWarnings({"ALL", "unchecked"})
@UtilityClass
public class ClassUtil {

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

    /**
     * @param instanceType may be not null
     * @param args         may be empty
     * @param <T>          required type
     * @return new Instance or throw IllegalArgumentException
     */
    public static <T> T createInstance(Class<T> instanceType, Object... args) {
        notNull(instanceType);

        if (args == null || args.length == 0) {

            return createInstanceImpl(instanceType);
        }

        try {

            Constructor<T> constructor = getConstructor(instanceType, args);

            Object[] arguments = filterArguments(constructor, args);

            return constructor.newInstance(arguments);

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

            throw new IllegalArgumentException("Unable to create new instance of Type " + instanceType, e);
        }
    }


    /**
     * @param instanceType may not be null
     * @param <T>          required type
     * @return Class array or null
     */
    public static <T> Class<?>[] findParameterTypes(Class<T> instanceType) {
        notNull(instanceType);

        Constructor<?>[] constructors = instanceType.getConstructors();

        return Stream.of(constructors)
                .filter(it -> it.getModifiers() == Modifier.PUBLIC)
                .map(Constructor::getParameterTypes)
                .filter(it -> it.length > 0).findAny().orElse(new Class[0]);
    }

    /**
     * get Parameter Type for Class type
     *
     * @param instanceType may not be null
     * @param <T>          required type
     * @return Class or null
     */
    public static <T> Class<T> getParameterType(Class<?> instanceType) {

        return getParameterType(instanceType, 0);
    }

    /**
     * get Parameter Type for Class type by given index
     *
     * @param instanceType may not be null
     * @param index        may not be negative
     * @param <T>          required type
     * @return Class or null or throw IllegalArgumentException
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getParameterType(Class<?> instanceType, int index) {
        notNull(instanceType);

        Class<?>[] parameterTypes = findParameterTypes(instanceType);

        if (parameterTypes.length == 0) {

            throw new IllegalArgumentException("unable to get ParameterType for " + instanceType);
        }

        return (Class<T>) parameterTypes[index];
    }

    /**
     * Get Constructor for give parameters
     *
     * @param instanceType may not be null
     * @param args         may empty
     * @param <T>          required type
     * @return Constructor or throw IllegalArgumentException
     */
    @SuppressWarnings("unchecked")
    public static <T> Constructor<T> getConstructor(Class<T> instanceType, Object... args) {
        notNull(instanceType);

        Constructor<?>[] constructors = instanceType.getConstructors();

        Constructor<?> fallBackConstructor = null;

        int argumentsLength = args == null ? 0 : args.length;

        for (Constructor<?> constructor : constructors) {

            int parameterTypeLength = constructor.getParameterTypes().length;

            if (parameterTypeLength == argumentsLength) {
                return (Constructor<T>) constructor;
            }

            fallBackConstructor = constructor;
        }

        IllegalArgumentException exception = new IllegalArgumentException("Unable to get public constructor of Type " + instanceType);

        return (Constructor<T>) Optional.ofNullable(fallBackConstructor).orElseThrow(() -> exception);
    }

    /**
     * Creates a new instance for the given type.
     *
     * @param instanceType may not be null
     * @param <T>          required type
     * @return new Object or throw Exception
     */
    @SuppressWarnings("unchecked")
    private static <T> T createInstanceImpl(Class<T> instanceType) {
        assert instanceType != null;

        if (instanceType.isAssignableFrom(Map.class)) {
            return (T) new HashMap<>();
        }

        if (instanceType.isAssignableFrom(List.class)) {
            return (T) new ArrayList<>();
        }

        if (instanceType.isAssignableFrom(Set.class)) {
            return (T) new HashSet<>();
        }

        if (instanceType.isAssignableFrom(Date.class)) {
            return (T) new Date();
        }

        Constructor<T> constructor = getConstructor(instanceType);

        Class<?>[] parameterTypes = constructor.getParameterTypes();

        if (parameterTypes.length > 0) {

            return createInstanceImpl(instanceType, parameterTypes);
        }

        try {
            return instanceType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {

            String format = "Class could not be instantiated with default constructor: %s %s";
            String message = String.format(format, e.getClass().getSimpleName(), e.getMessage());

            throw new IllegalArgumentException(message, e);
        }
    }

    private static <T> T createInstanceImpl(Class<T> instanceType, Class<?>... argumentTypes) {
        assert instanceType != null;

        Object[] parameterObjects = new Object[argumentTypes.length];

        for (int index = 0; index < argumentTypes.length; index++) {
            parameterObjects[index] = createInstanceImpl(argumentTypes[index]);
        }

        return createInstance(instanceType, parameterObjects);
    }

    private static <T> Object[] filterArguments(Constructor<T> constructor, Object[] args) {

        Class<?>[] parameterTypes = constructor.getParameterTypes();

        List<Object> list = new ArrayList<>();

        for (int index = 0; index < parameterTypes.length; index++) {

            Object obj = args.length < index ? null : args[index];
            list.add(obj);
        }

        return list.toArray();
    }

    /**
     * Get Generic Type at first index
     *
     * @param type should not be null
     * @param <T>  required type
     * @return Generic Type or null
     */
    public static <T> T getGenericType(Class<?> type) {

        return getGenericType(type, 0);
    }

    /**
     * @param type  Class Type
     * @param index may be not negative
     * @param <T>   required type
     * @return Generic Type or throw IndexOutOfBoundsException, IllegalArgumentException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getGenericType(Class<?> type, int index) {
        notNull(type);

        Type typeGenericSuperclass = type.getGenericSuperclass();

        if (typeGenericSuperclass instanceof ParameterizedType) {

            ParameterizedType genericSuperclass = (ParameterizedType) typeGenericSuperclass;

            Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();

            return (T) actualTypeArguments[index];
        } else {

            Type[] genericInterfaces = type.getGenericInterfaces();

            for (Type genericInterface : genericInterfaces) {

                if (genericInterface instanceof ParameterizedType) {

                    Type[] genericTypes = ((ParameterizedType) genericInterface).getActualTypeArguments();

                    return (T) genericTypes[index];
                }
            }
        }

        throw new IllegalArgumentException("Unable to get generic Type of: " + type);
    }


    /**
     * Get Generic Type of given type
     *
     * @param type my not be null
     * @param <T>  required type
     * @return Generic Type
     */
    @SuppressWarnings("unchecked")
    public static <T> T getGenericType(Type type) {
        notNull(type);

        return getGenericType(type, 0);
    }

    /**
     * Get Generic Type of given type by give index
     *
     * @param type  may not be null
     * @param index may be not negative
     * @param <T>   required type
     * @return Generic Type or null
     */
    @SuppressWarnings("unchecked")
    public static <T> T getGenericType(Type type, int index) {
        notNull(type);

        if (!(type instanceof ParameterizedType)) {
            return null;
        }

        ParameterizedType pt = (ParameterizedType) type;

        Type[] actualTypeArguments = pt.getActualTypeArguments();

        if (actualTypeArguments.length == 0) {
            return null;
        }

        Type actualTypeArgument = actualTypeArguments[index];

        if (actualTypeArgument instanceof ParameterizedType) {

            return (T) (((ParameterizedType) actualTypeArgument).getRawType());
        }

        return (T) actualTypeArgument;
    }

    private static boolean isDateType(Class<?> type) {

        return type == Date.class || type == LocalDateTime.class || type == LocalDate.class;
    }

}
