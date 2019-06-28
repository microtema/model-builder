package de.seven.fate.model.builder;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static de.seven.fate.model.builder.util.ModelBuilderUtil.randomCollectionSize;


public abstract class AbstractModelBuilder<T> implements ModelBuilder<T> {

    public List<T> list(boolean skip) {

        int size = randomCollectionSize();

        return list(size, skip);
    }

    protected List<T> list(int size, boolean skip) {

        List<T> list = new ArrayList<>();

        while (list.size() < size) {

            T model = build(null, skip, false);

            list.add(model);
        }

        return list;
    }

    public List<T> list(int size, boolean skip, boolean required) {

        List<T> list = new ArrayList<>();

        while (list.size() < size) {

            T model = build(null, skip, required);

            list.add(model);
        }

        return list;
    }

    protected Set<T> set(int size, boolean skip) {

        return new HashSet<>(list(size, skip));
    }

    public Set<T> set(int size, boolean skip, boolean required) {

        return new HashSet<>(list(size, skip, required));
    }

    public Set<T> set(boolean skip) {

        int collectionSize = randomCollectionSize();

        return set(collectionSize, skip);
    }


}
