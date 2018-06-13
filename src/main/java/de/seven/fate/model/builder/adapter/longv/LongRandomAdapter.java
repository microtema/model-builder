package de.seven.fate.model.builder.adapter.longv;

import de.seven.fate.model.builder.adapter.AbstractTypeRandomAdapter;

import java.util.Random;


public class LongRandomAdapter extends AbstractTypeRandomAdapter<Long> {

    public LongRandomAdapter() {
        registerPropertyAdapter(new IdPropertyRandomAdapter());
    }


    @Override
    protected Long randomValueDefault(String propertyName) {

        return new Random().nextLong();
    }


}
