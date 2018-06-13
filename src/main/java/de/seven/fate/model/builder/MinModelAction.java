package de.seven.fate.model.builder;

import de.seven.fate.commons.utils.FieldUtil;
import de.seven.fate.model.builder.adapter.TypeRandomAdapterFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MinModelAction implements ModelAction {

    private final boolean required;

    public MinModelAction(boolean required) {
        this.required = required;
    }

    @Override
    public Object execute(Field field, boolean overflow) {

        if (!isApplicableField(field, required)) {
            return null;
        }

        Object randomValue = TypeRandomAdapterFactory.getRandomValue(field.getType(), field.getName());

        if (randomValue != null) {
            return randomValue;
        }

        ModelBuilder<?> modelBuilder = ModelBuilderFactory.createBuilder(field.getType());

        return ((AbstractModelBuilder) modelBuilder).min(field, overflow, required);
    }

    private boolean isApplicableField(Field field, boolean required) {
        return !(field.getModifiers() == Modifier.FINAL || !required && !FieldUtil.isRequiredField(field));
    }
}
