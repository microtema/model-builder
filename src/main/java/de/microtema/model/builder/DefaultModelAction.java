package de.microtema.model.builder;

import de.microtema.model.builder.adapter.TypeRandomAdapterFactory;
import de.microtema.model.builder.util.FieldUtil;
import de.microtema.model.builder.util.MethodUtil;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;


public class DefaultModelAction implements ModelAction {

    private final boolean requiredField;

    DefaultModelAction(boolean requiredField) {
        this.requiredField = requiredField;
    }

    /**
     * @param method   may not be null
     * @param overflow may be true or false, detect when is stack overflow
     * @return build value or null if not requiredField
     */
    @Override
    public Object execute(final Method method, final boolean overflow, final boolean random) {
        Validate.notNull(method);

        if (!isApplicableProperty(method)) {

            return null;
        }

        String property = MethodUtil.getPropertyName(method.getName());

        Object randomValue = TypeRandomAdapterFactory.getValue(method.getReturnType(), property, random);

        return Optional.ofNullable(randomValue).orElseGet(() -> {

            Class<?> modelType = method.getReturnType();

            ModelBuilder<?> modelBuilder = ModelBuilderFactory.createBuilder(modelType);

            return ((AbstractModelBuilder) modelBuilder).build(method, new DefaultModelAction(overflow), requiredField, random);
        });
    }

    /*
     * return true if property is requiredField or onnotated as requiredField
     */
    private boolean isApplicableProperty(Method method) {
        assert method != null;

        if (requiredField) {

            return true;
        }

        String property = MethodUtil.getPropertyName(method.getName());

        Class<?> declaringClass = method.getDeclaringClass();

        Optional<Field> fieldOptional = Optional.ofNullable(FieldUtils.getField(declaringClass, property, true));

        return fieldOptional.map(FieldUtil::isRequiredField).orElseGet(() -> MethodUtil.isRequiredMethod(method));
    }
}
