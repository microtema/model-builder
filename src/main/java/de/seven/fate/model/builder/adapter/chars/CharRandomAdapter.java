package de.seven.fate.model.builder.adapter.chars;

import de.seven.fate.model.builder.adapter.AbstractTypeRandomAdapter;

public class CharRandomAdapter extends AbstractTypeRandomAdapter<Character> {

    @Override
    protected Character randomValueDefault(String propertyName) {

        return (char) RANDOM.nextInt();
    }
}
