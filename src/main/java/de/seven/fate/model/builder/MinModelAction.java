package de.seven.fate.model.builder;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static de.seven.fate.model.builder.ModelBuilderFactory.createBuilder;
import static de.seven.fate.model.util.FieldUtil.isRequiredField;

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

        ModelBuilder<?> modelBuilder = createBuilder(field.getType());

        return ((AbstractModelBuilder) modelBuilder).min(field, overflow, required);
    }

    private boolean isApplicableField(Field field, boolean required) {
        return !(field.getModifiers() == Modifier.FINAL || !required && !isRequiredField(field));
    }
}
