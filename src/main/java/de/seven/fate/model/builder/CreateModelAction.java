package de.seven.fate.model.builder;

import java.lang.reflect.Field;

public interface CreateModelAction {
    Object execute(Field field);
}
