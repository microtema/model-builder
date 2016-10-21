package de.seven.fate.model.adapter;

import de.seven.fate.model.adapter.bool.BooleanRandomAdapter;
import de.seven.fate.model.adapter.date.DateRandomAdapter;
import de.seven.fate.model.adapter.decimal.BigDecimalRandomAdapter;
import de.seven.fate.model.adapter.doublev.DoubleTypeRandomAdapter;
import de.seven.fate.model.adapter.integer.IntegerRandomAdapter;
import de.seven.fate.model.adapter.longv.LongRandomAdapter;
import de.seven.fate.model.adapter.string.StringRandomAdapter;
import de.seven.fate.model.adapter.url.UrlRandomAdapter;
import de.seven.fate.model.builder.CreateModelAction;
import de.seven.fate.model.util.ClassUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Mario on 24.03.2016.
 */
@SuppressWarnings("ALL")
public final class TypeRandomAdapterFactory {

    private static final Logger LOGGER = Logger.getLogger(TypeRandomAdapterFactory.class.getName());

    private static final Map<Class<?>, AbstractTypeRandomAdapter<?>> ADAPTERS = Collections.synchronizedMap(new HashMap<Class<?>, AbstractTypeRandomAdapter<?>>());

    private static final AbstractTypeRandomAdapter DEFAULT_ADAPTER = new DefaultTypeRandomAdapter();

    static {
        registerAdapter(new StringRandomAdapter());
        registerAdapter(new UrlRandomAdapter());
        registerAdapter(new BooleanRandomAdapter());
        registerAdapter(new IntegerRandomAdapter());
        registerAdapter(new DateRandomAdapter());
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


    public static <T> void generateRandomFieldValues(T model, CreateModelAction createAction) {

        List<Field> fields = ClassUtil.getAllFields(model.getClass());

        for (Field field : fields) {

            String fieldName = field.getName();

            Object propertyValue = createAction.execute(field);

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
