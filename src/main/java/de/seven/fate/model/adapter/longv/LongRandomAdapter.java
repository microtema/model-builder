package de.seven.fate.model.adapter.longv;

import de.seven.fate.model.adapter.AbstractTypeRandomAdapter;

import java.util.Random;

/**
 * Created by Mario on 24.03.2016.
 */
public class LongRandomAdapter extends AbstractTypeRandomAdapter<Long> {

    public LongRandomAdapter() {
        registerPropertyRandomAdapter(new IdPropertyRandomAdapter());
    }


    @Override
    protected Long randomValueDefault(String propertyName) {

        return new Random().nextLong();
    }


}
