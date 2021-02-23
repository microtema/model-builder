package de.microtema.model.builder.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.Validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;


@UtilityClass
public class AnnotationUtil {

    private static final Map<String, Predicate<Object>> PREDICATES = new HashMap<>();

    static {

        addPredicate("NotNull", it -> true);
        addPredicate("NotEmpty", it -> true);
        addPredicate("NotBlank", it -> true);
    }

    /**
     * @param predicateName may not be null
     * @param predicate     may not be null
     * @return the previous value associated with predicateName, or
     * predicateName if there was no mapping for predicateName.
     */
    public static Predicate<Object> addPredicate(String predicateName, Predicate<Object> predicate) {

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

        Predicate<Object> function = PREDICATES.get(annotationName);

        if (function == null) {

            return false;
        }

        return function.test(annotation);
    }

    private static String getAnnotationName(Object annotation) {
        Validate.notNull(annotation);

        String annotationName = annotation.getClass().getSimpleName();

        if (!(annotation instanceof Proxy)) {

            return annotationName;
        }

        Proxy proxy = (Proxy) annotation;

        Class<?>[] classes = proxy.getClass().getInterfaces();

        return classes[0].getSimpleName();
    }
}
