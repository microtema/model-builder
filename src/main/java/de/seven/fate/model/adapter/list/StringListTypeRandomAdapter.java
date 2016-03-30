package de.seven.fate.model.adapter.list;

import de.seven.fate.model.adapter.string.StringRandomAdapter;

import java.util.List;

/**
 * Created by Mario on 30.03.2016.
 */
public class StringListTypeRandomAdapter extends AbstractListTypeRandomAdapter<String> {

    private final StringRandomAdapter stringRandomAdapter;

    public StringListTypeRandomAdapter(StringRandomAdapter stringRandomAdapter) {
        this.stringRandomAdapter = stringRandomAdapter;
    }

    @Override
    protected List<String> randomValueDefault(String propertyName) {
        return randomList(stringRandomAdapter);
    }


}
