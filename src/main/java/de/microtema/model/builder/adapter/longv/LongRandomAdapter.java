package de.microtema.model.builder.adapter.longv;

import de.microtema.model.builder.adapter.AbstractTypeRandomAdapter;


public class LongRandomAdapter extends AbstractTypeRandomAdapter<Long> {

    public LongRandomAdapter() {
        registerPropertyAdapter(new IdPropertyRandomAdapter());
    }


    @Override
    protected Long randomValueDefault(String propertyName) {

        return RANDOM.nextLong();
    }


    @Override
    public Long fixValue(String propertyName) {

        return (long) propertyName.length();
    }
}
