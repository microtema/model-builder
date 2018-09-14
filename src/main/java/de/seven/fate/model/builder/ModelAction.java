package de.seven.fate.model.builder;

import java.lang.reflect.Method;

@FunctionalInterface
public interface ModelAction {

    Object execute(Method method, boolean overflow);
}
