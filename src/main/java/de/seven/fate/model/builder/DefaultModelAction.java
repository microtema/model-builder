package de.seven.fate.model.builder;

import de.seven.fate.model.builder.adapter.TypeRandomAdapterFactory;
import de.seven.fate.model.builder.util.FieldUtil;
import de.seven.fate.model.builder.util.MethodUtil;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

public class DefaultModelAction implements ModelAction {

    private final boolean required;

    public DefaultModelAction(boolean required) {
        this.required = required;
    }

    @Override
    public Object execute(Method method, boolean overflow) {

        if (!isApplicableField(method)) {
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

    private boolean isApplicableField(Method method) {
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
