package de.seven.fate.model.adapter;

import de.seven.fate.model.adapter.bool.BooleanRandomAdapter;
import de.seven.fate.model.adapter.date.DateRandomAdapter;
import de.seven.fate.model.adapter.decimal.BigDecimalRandomAdapter;
import de.seven.fate.model.adapter.doublev.DoubleTypeRandomAdapter;
import de.seven.fate.model.adapter.integer.IntegerRandomAdapter;
import de.seven.fate.model.adapter.longv.LongRandomAdapter;
import de.seven.fate.model.adapter.string.StringRandomAdapter;
import de.seven.fate.model.adapter.url.UrlRandomAdapter;
import de.seven.fate.model.builder.AbstractModelBuilder;
import de.seven.fate.model.builder.CreateModelAction;
import de.seven.fate.model.builder.ModelBuilder;
import de.seven.fate.model.util.ClassUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static de.seven.fate.model.builder.ModelBuilderFactory.createBuilder;

/**
 * Created by Mario on 24.03.2016.
 */
@SuppressWarnings("ALL")
public final class TypeRandomAdapterFactory {

    private static final Logger LOGGER = Logger.getLogger(TypeRandomAdapterFactory.class.getName());

    private static final Map<Class<?>, AbstractTypeRandomAdapter<?>> ADAPTERS = Collections.synchronizedMap(new HashMap<Class<?>, AbstractTypeRandomAdapter<?>>());

    static {
        registerAdapter(new StringRandomAdapter());
        registerAdapter(new UrlRandomAdapter());
        registerAdapter(new BooleanRandomAdapter());
        registerAdapter(new DateRandomAdapter());
        registerAdapter(new IntegerRandomAdapter());
        registerAdapter(new BigDecimalRandomAdapter());
        registerAdapter(new DoubleTypeRandomAdapter());
        registerAdapter(new LongRandomAdapter());
    }

    private TypeRandomAdapterFactory() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }


    public static <T> AbstractTypeRandomAdapter<T> lookupAdapter(Class<T> valueType) {
        assert valueType != null;

        return (AbstractTypeRandomAdapter<T>) ADAPTERS.get(valueType);
    }


    public static <T> T getRandomPropertyValue(Class<T> propertyType) {

        return getRandomPropertyValue(propertyType, null);
    }

    public static <T> T getRandomPropertyValue(Class<T> propertyType, String propertyName) {
        assert propertyType != null;

        if (ADAPTERS.containsKey(propertyType)) {
            return (T) ADAPTERS.get(propertyType).randomValueDefault(propertyName);
        }

        return null;
    }

    public static <T> T getCollection(Class<T> modelType, Type propertyType, boolean skip) {

        if (List.class.isAssignableFrom(modelType)) {

            Class<?> genericType = ClassUtil.getActualTypeArgument(propertyType);

            ModelBuilder<?> builder = createBuilder(genericType);

            return (T) ((AbstractModelBuilder) builder).list(skip);

        } else if (Set.class.isAssignableFrom(modelType)) {

            Class<?> genericType = ClassUtil.getActualTypeArgument(propertyType);

            ModelBuilder<?> builder = createBuilder(genericType);

            return (T) ((AbstractModelBuilder) builder).set(skip);

        }

        return null;
    }

    public static <T> void generateRandomFieldValues(T model, CreateModelAction createAction, boolean skip) {
        assert model != null;
        assert createAction != null;

        Class<?> modelClass = model.getClass();
        List<Field> fields = ClassUtil.getAllFields(modelClass);

        for (Field field : fields) {

            String fieldName = field.getName();
            Class<?> fieldType = field.getType();
            Class<?> fieldGenericType = ClassUtil.getActualTypeArgument(field.getGenericType());

            boolean overFlow = fieldGenericType == modelClass;

            if (overFlow && skip) {
                continue;
            }

            Object propertyValue = createAction.execute(field, overFlow);

            try {
                BeanUtils.setProperty(model, fieldName, propertyValue);
            } catch (IllegalAccessException | InvocationTargetException e) {
                LOGGER.log(Level.WARNING, "Unable to set property: " + fieldName + " on model: " + model, e);
            }
        }
    }


    private static void registerAdapter(AbstractTypeRandomAdapter<?> valueAdapter) {
        assert valueAdapter != null;

        ADAPTERS.put(valueAdapter.getValueType(), valueAdapter);
    }
}
