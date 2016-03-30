package de.seven.fate.model.adapter.list;

import de.seven.fate.model.adapter.AbstractTypeRandomAdapter;
import de.seven.fate.model.util.NumberUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mario on 30.03.2016.
 */
public abstract class AbstractListTypeRandomAdapter<E> extends AbstractTypeRandomAdapter<List<E>> {


    protected List<E> randomList(AbstractTypeRandomAdapter<E> randomAdapter) {

        int collectionSize = NumberUtil.random(1, 10);

        List<E> list = new ArrayList<>();

        while (list.size() < collectionSize) {
            list.add(randomAdapter.randomValue(null));
        }

        return list;
    }
}
