package de.seven.fate.model.util;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@SuppressWarnings({"ALL", "unchecked"})
public final class FieldUtil {

    private static final Logger LOGGER = Logger.getLogger(FieldUtil.class.getName());

    private FieldUtil() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }

    public static List<Field> getAllFields(Class<?> type) {
        assert type != null;

        if (type.getSuperclass() != null) {

            List<Field> fields = getAllFields(type.getSuperclass());

            fields.addAll(filterFields(type.getDeclaredFields()));

            return fields;
        }

        return filterFields(type.getDeclaredFields());
    }

    private static List<Field> filterFields(Field[] fields) {

        List<Field> list = new ArrayList<>();

        for (Field field : fields) {
            if ("class".equals(field.getName())) {
                continue;
            }

            if (field.getName().startsWith("$")) {
                continue;
            }

            list.add(field);
        }
        return list;
    }

    public static boolean isRequiredField(Field field) {
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
            return xmlElement.required();
        }

        return false;
    }
}
