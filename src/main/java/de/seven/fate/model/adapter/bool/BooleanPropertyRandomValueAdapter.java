package de.seven.fate.model.adapter.bool;

import de.seven.fate.model.adapter.AbstractPropertyRandomValueAdapter;

import java.util.Random;

/**
 * Created by Mario on 24.03.2016.
 */
public class BooleanPropertyRandomValueAdapter extends AbstractPropertyRandomValueAdapter<Boolean> {

    @Override
    protected Boolean randomValueImpl(String propertyName, Class<?> objectType) {

        return new Random().nextBoolean();
    }

}
