package de.seven.fate.model.util;

import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
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

    public static <T> Constructor<T> getConstructor(Class<T> instanceType) {

        Constructor<?>[] constructors = instanceType.getConstructors();

        for (Constructor<?> constructor : constructors) {

            if (constructor.getModifiers() == Modifier.PUBLIC) {
                return (Constructor<T>) constructor;
            }
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

    public static <T> T createInstance(Constructor<T> constructor, Object... args) {
        assert constructor != null;

        try {
            return constructor.newInstance(args);
        } catch (Exception e) {

            LOGGER.log(Level.SEVERE, "unable to create new instance of Type " + constructor, e);
        }

        throw new IllegalArgumentException("unable to create new instance of Type " + constructor);
    }

    public static boolean isComplexType(Class<?> type) {

        return !type.isPrimitive() && type != String.class && !ClassUtils.isPrimitiveOrWrapper(type) && type != Date.class && type != BigDecimal.class;
    }

    public static <T> boolean isCollectionType(Class<T> type) {
        return Collection.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type);
    }


}
