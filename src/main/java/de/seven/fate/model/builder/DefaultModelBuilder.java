package de.seven.fate.model.builder;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultModelBuilder<T> extends AbstractModelBuilder<T> {


    @Override
    public List<T> list(int size) {

        return set(size).stream().collect(Collectors.toList());
    }

    @Override
    public Set<T> set(int size) {

        Set<T> set = new HashSet<>();

        fillCollection(size, set);

        return set;
    }
}
