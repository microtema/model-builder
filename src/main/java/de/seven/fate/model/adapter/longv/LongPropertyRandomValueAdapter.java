package de.seven.fate.model.adapter.longv;

import de.seven.fate.model.adapter.AbstractPropertyRandomValueAdapter;

import java.util.Random;

/**
 * Created by Mario on 24.03.2016.
 */
public class LongPropertyRandomValueAdapter extends AbstractPropertyRandomValueAdapter<Long> {

    public LongPropertyRandomValueAdapter() {
        registerRandomAdapter(new IdRandomAdapter());
    }


    @Override
    protected Long randomValueImpl(String propertyName, Class<?> objectType) {

        return new Random().nextLong();
    }


}
