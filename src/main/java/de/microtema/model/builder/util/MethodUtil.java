package de.microtema.model.builder.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.text.WordUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class MethodUtil {

    static final Map<Class<?>, Set<String>> PROPERTIES_CACHE = new ConcurrentHashMap<>(256);

    private static final Set<String> NOT_NULL_CONSTRAINTS = Stream.of("NotNull", "NotEmpty", "NotBlank").collect(Collectors.toSet());

    /**
     * @param beanType may not be null
     * @return Set of properties
     */
    public static Set<String> getProperties(Class<?> beanType) {
        Validate.notNull(beanType);

        if (PROPERTIES_CACHE.containsKey(beanType)) {

            return PROPERTIES_CACHE.get(beanType);
        }

        Method[] methods = beanType.getMethods();

        Set<String> getterMethods = Stream.of(methods).filter(it -> Modifier.isPublic(it.getModifiers()) && it.getName().startsWith("get") && it.getParameterCount() == 0).map(Method::getName).map(MethodUtil::getPropertyName).map(WordUtils::uncapitalize).collect(Collectors.toSet());
        Set<String> setterMethods = Stream.of(methods).filter(it -> Modifier.isPublic(it.getModifiers()) && it.getName().startsWith("set") && it.getParameterCount() == 1).map(Method::getName).map(MethodUtil::getPropertyName).map(WordUtils::uncapitalize).collect(Collectors.toSet());

        getterMethods.removeIf(it -> !setterMethods.contains(it));
        setterMethods.removeIf(it -> !getterMethods.contains(it));

        PROPERTIES_CACHE.put(beanType, getterMethods);

        return getterMethods;
    }

    /**
     * @param bean     may not be null
     * @param property may not be null
     * @param value    may be null
     */
    public static void setProperties(Object bean, String property, Object value) {
        Validate.notNull(bean);
        Validate.notEmpty(property);

        Method setterMethod = getSetterMethod(bean.getClass(), property);

        try {

            //NOTE: Object Array is need it here to prevent expansion into varargs
            setterMethod.invoke(bean, value);

        } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {

            throw new IllegalArgumentException("Unable to set value: " + value + " to property: " + property + " on bean: " + bean.getClass(), e);
        }
    }

    public static Method getGetterMethod(Class<?> beanType, String propertyName) {
        Validate.notNull(beanType);
        Validate.notEmpty(propertyName);

        String methodName = convertToGetterMethod(propertyName);

        return MethodUtils.getAccessibleMethod(beanType, methodName, new Class<?>[]{});
    }

    public static Method getSetterMethod(Class<?> beanType, String propertyName) {
        Validate.notNull(beanType);
        Validate.notEmpty(propertyName);

        String methodName = convertToSetterMethod(propertyName);

        Class<?> parameterType = getParameterType(beanType, propertyName);

        try {
            return beanType.getMethod(methodName, parameterType);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Unable to get method", e);
        }
    }

    public static Method findStaticMethod(Class<?> beanType, Function<Method, Boolean> filter) {
        Validate.notNull(beanType);

        Method[] methods = beanType.getMethods();

        for (Method method : methods) {

            if (Modifier.isStatic(method.getModifiers()) && filter.apply(method)) {
                return method;
            }
        }

        return null;
    }

    public static String getPropertyName(String methodName) {
        Validate.notEmpty(methodName);

        String property = methodName.replaceFirst("get", StringUtils.EMPTY).replaceFirst("set", StringUtils.EMPTY);

        return WordUtils.uncapitalize(property);
    }

    /**
     * Return true if is Required Field else false
     *
     * @param method may not be null
     * @return boolean
     */
    public static boolean isRequiredMethod(Method method) {
        Validate.notNull(method);

        Annotation[] annotations = method.getAnnotations();

        for (Annotation annotation : annotations) {

            Class<? extends Annotation> annotationType = annotation.annotationType();
            String annotationTypeName = annotationType.getName();

            if (NOT_NULL_CONSTRAINTS.contains(annotationTypeName)) {
                return true;
            }
        }

        return false;
    }

    private static String convertToGetterMethod(String property) {
        assert property != null;

        return "get" + WordUtils.capitalize(property);
    }

    private static String convertToSetterMethod(String property) {
        assert property != null;

        return "set" + WordUtils.capitalize(property);
    }

    private static Class<?> getParameterType(Class<?> beanType, String property) {
        assert beanType != null;
        assert property != null;

        Method method = getGetterMethod(beanType, property);

        return method.getReturnType();
    }
}
