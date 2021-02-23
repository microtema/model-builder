package de.microtema.model.builder.util;

import de.microtema.model.builder.ModelAction;
import de.microtema.model.builder.ModelBuilder;
import de.microtema.model.builder.ModelBuilderFactory;
import de.microtema.model.builder.adapter.TypeRandomAdapterFactory;
import de.microtema.model.builder.constants.Constants;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.Validate;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.*;

@UtilityClass
public final class ModelBuilderUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * @return random collection size
     */
    public static int randomCollectionSize() {

        return Math.max(Constants.MIN_COLLECTION_SIZE, new SecureRandom().nextInt(Constants.MAX_COLLECTION_SIZE));
    }

    /**
     * @param resourceLocation may not be null
     * @return Properties from properties files
     */
    public static Properties fromProperties(String resourceLocation) {
        Validate.notNull(resourceLocation, "resourceLocation");

        Properties properties = new Properties();

        try (InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation)) {

            properties.load(Validate.notNull(resourceAsStream));
        } catch (IOException | NullPointerException e) {

            throw new IllegalArgumentException(e);
        }

        return properties;
    }

    /**
     * @param resourceLocation may not be null
     * @return Properties from xml files
     */
    public static Properties fromXml(String resourceLocation) {
        Validate.notNull(resourceLocation, "resourceLocation");

        Properties properties = new Properties();

        try (InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation)) {

            properties.loadFromXML(Validate.notNull(resourceAsStream));
        } catch (IOException | NullPointerException e) {

            throw new IllegalArgumentException(e);
        }

        return properties;
    }

    public static <T> T createModel(Class<T> instanceType, ModelAction modelAction, boolean skip, boolean random) {

        if (instanceType.isInterface()) {

            return ModelBuilderUtil.createInterface(instanceType, modelAction, skip, random);
        }

        Constructor<T> constructor = ClassUtil.findConstructor(instanceType);

        if (Objects.nonNull(constructor)) {

            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Object[] args = TypeRandomAdapterFactory.getParameters(parameterTypes);

            return ClassUtil.createInstance(instanceType, args);
        }

        return createModelFromStaticConstructor(instanceType, modelAction, skip, random);
    }

    public static <T> T createModelFromStaticConstructor(Class<T> instanceType, ModelAction modelAction, boolean skip, boolean random) {

        Method staticMethod = MethodUtil.findStaticMethod(instanceType, it -> {
            Class<?> genericType = ClassUtil.getGenericType(it.getGenericReturnType());
            return genericType.isAssignableFrom(instanceType);
        });

        if (Objects.isNull(staticMethod)) {

            String format = "Class could not be instantiated with static constructor: %s";
            String message = String.format(format, instanceType.getClass().getSimpleName());

            throw new IllegalArgumentException(message);
        }

        Class<?>[] parameterTypes = staticMethod.getParameterTypes();

        Object[] args = TypeRandomAdapterFactory.getParameters(parameterTypes);

        try {
            return (T) staticMethod.invoke(null, args);
        } catch (IllegalAccessException | InvocationTargetException e) {

            String format = "Class could not be instantiated with static constructor: %s %s";
            String message = String.format(format, e.getClass().getSimpleName(), e.getMessage());

            throw new IllegalArgumentException(message, e);
        }
    }

    public static <T> T createInterface(Class<T> clazz, ModelAction modelAction, boolean skip, boolean random) {

        ProxyFactory factory = new ProxyFactory();
        factory.setInterfaces(new Class[]{clazz});
        factory.setFilter(m -> !m.getName().equals("finalize"));

        MethodHandler handler = createDefaultMethodHandler(modelAction, skip, random);

        try {
            return (T) factory.create(new Class<?>[0], new Object[0], handler);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MethodHandler createDefaultMethodHandler(ModelAction modelAction, boolean skip, boolean random) {

        Map<String, Object> cache = new HashMap<>();

        return (self, method, proceed, args) -> {

            String className = self.getClass().getName();
            String methodName = method.getName();
            String key = className + ":" + methodName;

            if (!cache.containsKey(key)) {

                Class<?> genericType = ClassUtil.getGenericType(method.getGenericReturnType());

                ModelBuilder<?> builder = ModelBuilderFactory.createBuilder(genericType);

                Object value = builder.build(method, modelAction, skip, random);

                cache.put(key, value);
            }

            return cache.get(key);
        };
    }

    public static <T> T fromJson(String resourceLocation, Class<T> beanType) {

        try (InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation)) {

            return mapper.readValue(resourceAsStream, beanType);
        } catch (IOException | NullPointerException e) {

            throw new IllegalArgumentException(e);
        }
    }

    public static <T> Collection<T> collectionFromJson(String resourceLocation, Class<? extends Collection> collectionType, Class<T> beanType) {

        try (InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation)) {

            Object collection = mapper.readValue(resourceAsStream, mapper.getTypeFactory().constructCollectionType(collectionType, beanType));

            return (Collection<T>) collection;
        } catch (IOException | NullPointerException e) {

            throw new IllegalArgumentException(e);
        }
    }
}
