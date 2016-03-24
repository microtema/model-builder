package de.seven.fate.model.builder;

import java.util.List;
import java.util.Set;

public interface ModelBuilder<T> {


    /**
     * Generic Model Typ
     */
    Class<T> getGenericType();

    /**
     * Create new Instance of Model and init only required fields
     */
    T min();

    /**
     * Create new Instance of Model and init all fields
     */
    T max();

    /**
     * Create new Instance of Model and init as Min or Max
     */
    T random();

    /**
     * Create new List by random size with new Instances of Models and init as Min or Max
     */
    List<T> list();

    /**
     * Create new Set by random size with new Instances of Models and init as Min or Max
     */
    List<T> list(int size);

    /**
     * Create new Set by random size with new Instances of Models and init as Min or Max
     */
    Set<T> set();

    /**
     * Create new Set by fixed size with new Instances of Models and init as Min or Max
     */
    Set<T> set(int size);

}
