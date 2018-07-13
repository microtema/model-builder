package de.seven.fate.model.builder;

import java.util.List;
import java.util.Set;

public interface ModelBuilder<T> {

    /**
     * @return Generic Model Typ
     */
    Class<T> getGenericType();

    /**
     * @return field
     */
    default Class[] getActualTypeArguments() {
        return new Class[0];
    }

    /**
     * @return new Instance of Model and init only required fields
     */
    T min();

    /**
     * @return new Instance of Model and init all fields on top level.
     */
    T max();

    /**
     * @return new Instance of Model and init as Min or Max
     */
    T random();

    /**
     * @return new Instance of Model and init as Fix
     */
    T fix();

    /**
     * @param resourceLocation may not be empty
     * @return @return new Instance of Model created from Resource
     */
    T fromResource(String resourceLocation);

    /**
     * @return new List by random size with new Instances of Models and init as Min or Max
     */
    List<T> list();

    /**
     * @param size of Collection
     * @return new Set by random size with new Instances of Models and init as Min or Max
     */
    List<T> list(int size);

    /**
     * @return new Set by random size with new Instances of Models and init as Min or Max
     */
    Set<T> set();

    /**
     * @param size of Collection
     * @return new Set by fixed size with new Instances of Models and init as Min or Max
     */
    Set<T> set(int size);

}
