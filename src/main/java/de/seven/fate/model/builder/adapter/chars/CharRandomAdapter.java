package de.seven.fate.model.builder.adapter.chars;

import de.seven.fate.model.builder.adapter.AbstractTypeRandomAdapter;

import java.util.Random;

public class CharRandomAdapter extends AbstractTypeRandomAdapter<Character> {

    @Override
    protected Character randomValueDefault(String propertyName) {

        return (char) new Random().nextInt();
    }
}
