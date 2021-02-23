package de.microtema.model.builder;


import de.microtema.model.builder.util.ModelBuilderUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class AbstractModelBuilder<T> implements ModelBuilder<T> {

    public List<T> list(boolean skip) {

        int size = ModelBuilderUtil.randomCollectionSize();

        return list(size, skip);
    }

    protected List<T> list(int size, boolean skip) {

        return list(size, skip, false);
    }

    public List<T> list(int size, boolean skip, boolean required) {

        List<T> list = new ArrayList<>();

        while (list.size() < size) {

            T model = build(null, new DefaultModelAction(required), skip, true);

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

        int collectionSize = ModelBuilderUtil.randomCollectionSize();

        return set(collectionSize, skip);
    }


}
