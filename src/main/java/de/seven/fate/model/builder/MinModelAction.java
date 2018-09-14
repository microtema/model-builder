package de.seven.fate.model.builder;

import de.seven.fate.model.builder.adapter.TypeRandomAdapterFactory;
import de.seven.fate.model.builder.util.FieldUtil;
import de.seven.fate.model.builder.util.MethodUtil;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

public class MinModelAction implements ModelAction {

    private final boolean required;

    public MinModelAction(boolean required) {
        this.required = required;
    }

    /**
     * @param method   may not be null
     * @param overflow may be true or false
     * @return min value or null if not required
     */
    @Override
    public Object execute(Method method, boolean overflow) {
        Validate.notNull(method);

        if (!isApplicableProperty(method)) {
            return null;
        }

        String property = MethodUtil.getPropertyName(method.getName());

        Object randomValue = TypeRandomAdapterFactory.getRandomValue(method.getReturnType(), property);

        if (randomValue != null) {
            return randomValue;
        }

        Class<?> modelType = method.getReturnType();

        ModelBuilder<?> modelBuilder = ModelBuilderFactory.createBuilder(modelType);

        return ((AbstractModelBuilder) modelBuilder).min(method, overflow, required);
    }

    /*
     * return true if property is required or onnotated as required
     */
    private boolean isApplicableProperty(Method method) {
        assert method != null;

        if (required) {

            return true;
        }

        String property = MethodUtil.getPropertyName(method.getName());

        Class<?> declaringClass = method.getDeclaringClass();

        Optional<Field> fieldOptional = Optional.ofNullable(FieldUtils.getField(declaringClass, property, true));

        return fieldOptional.map(FieldUtil::isRequiredField).orElseGet(() -> MethodUtil.isRequiredMethod(method));
    }
}
