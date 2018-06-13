package de.seven.fate.model.builder;

import java.lang.reflect.Field;

@FunctionalInterface
public interface ModelAction {
    Object execute(Field field, boolean overflow);
}
