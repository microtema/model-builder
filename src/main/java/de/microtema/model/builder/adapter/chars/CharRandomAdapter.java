package de.microtema.model.builder.adapter.chars;

import de.microtema.model.builder.adapter.AbstractTypeRandomAdapter;

public class CharRandomAdapter extends AbstractTypeRandomAdapter<Character> {

    @Override
    protected Character randomValueDefault(String propertyName) {

        return (char) RANDOM.nextInt();
    }

    @Override
    public Character fixValue(String propertyName) {

        return propertyName.charAt(0);
    }
}
