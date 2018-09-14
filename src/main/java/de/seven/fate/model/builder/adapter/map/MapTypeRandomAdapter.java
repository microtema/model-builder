package de.seven.fate.model.builder.adapter.map;

import de.seven.fate.model.builder.adapter.AbstractTypeRandomAdapter;
import de.seven.fate.model.builder.adapter.string.StringRandomAdapter;

import java.util.HashMap;
import java.util.Map;

public class MapTypeRandomAdapter extends AbstractTypeRandomAdapter<Map> {

    private static final int MAX_SIZE = 5;

    private final StringRandomAdapter stringRandomAdapter;

    public MapTypeRandomAdapter(StringRandomAdapter stringRandomAdapter) {
        this.stringRandomAdapter = stringRandomAdapter;
    }

    @Override
    protected Map randomValueDefault(String propertyName) {

        Map<String, Object> map = new HashMap<>();

        for (int index = 0; index < MAX_SIZE; index++) {
            map.put("key-" + index, stringRandomAdapter.randomValueDefault(propertyName));
        }

        return map;
    }
}
