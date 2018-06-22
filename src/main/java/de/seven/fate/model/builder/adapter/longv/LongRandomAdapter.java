package de.seven.fate.model.builder.adapter.longv;

import de.seven.fate.model.builder.adapter.AbstractTypeRandomAdapter;


public class LongRandomAdapter extends AbstractTypeRandomAdapter<Long> {

    LongRandomAdapter() {
        registerPropertyAdapter(new IdPropertyRandomAdapter());
    }


    @Override
    protected Long randomValueDefault(String propertyName) {

        return RANDOM.nextLong();
    }


}
