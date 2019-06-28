package de.seven.fate.model.builder.util;

import org.apache.commons.lang3.Validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Annotation Util
 */
public final class AnnotationUtil {

    private static final Map<String, Function<Object, Boolean>> PREDICATES = new HashMap<>();

    static {

        addPredicate("javax.xml.bind.annotation.XmlAttribute", it -> true);
        addPredicate("javax.xml.bind.annotation.XmlElement", it -> true);

        addPredicate("javax.validation.constraints.NotNull", it -> true);
        addPredicate("javax.validation.constraints.NotEmpty", it -> true);
    }

    private AnnotationUtil() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }

    /**
     * @param predicateName may not be null
     * @param predicate     may not be null
     * @return the previous value associated with predicateName, or
     * predicateName if there was no mapping for predicateName.
     */
    public static Function<Object, Boolean> addPredicate(String predicateName, Function<Object, Boolean> predicate) {

        return PREDICATES.put(predicateName, predicate);
    }

    /**
     * Return true if is Required Field else false
     *
     * @param annotations may not be null
     * @return boolean
     */
    public static boolean isAnyRequiredAnnotation(Annotation[] annotations) {

        if (annotations == null) {
            return false;
        }

        return Stream.of(annotations).anyMatch(AnnotationUtil::isRequiredAnnotation);
    }

    /**
     * Return true if is Required Field else false
     *
     * @param annotation may not be null
     * @return boolean
     */
    public static boolean isRequiredAnnotation(Object annotation) {
        Validate.notNull(annotation);

        String annotationName = getAnnotationName(annotation);

        Function<Object, Boolean> function = PREDICATES.get(annotationName);

        if (function == null) {

            return false;
        }

        return function.apply(annotation);
    }

    private static String getAnnotationName(Object annotation) {
        Validate.notNull(annotation);

        String annotationName = annotation.getClass().getName();

        if (!(annotation instanceof Proxy)) {

            return annotationName;
        }

        Proxy proxy = (Proxy) annotation;

        Class<?>[] classes = proxy.getClass().getInterfaces();

        return classes[0].getName();
    }
}