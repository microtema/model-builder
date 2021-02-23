package de.microtema.model.builder;

import java.lang.reflect.Method;

@FunctionalInterface
public interface ModelAction {

    Object execute(Method method, boolean overflow, boolean randomValue);
}
