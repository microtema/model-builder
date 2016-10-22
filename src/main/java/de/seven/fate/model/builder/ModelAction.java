package de.seven.fate.model.builder;

import java.lang.reflect.Field;

public interface ModelAction {
    Object execute(Field field, boolean overflow);
}
