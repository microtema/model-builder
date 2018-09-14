package de.seven.fate.model.builder.adapter.integer;

import de.seven.fate.model.builder.adapter.AbstractTypeRandomAdapter;


public class IntegerRandomAdapter extends AbstractTypeRandomAdapter<Integer> {

    public IntegerRandomAdapter() {

        registerPropertyAdapter(
                new PositionPropertyRandomAdapter(),
                new ZipCodePropertyRandomAdapter());
    }


    @Override
    protected Integer randomValueDefault(String propertyName) {

        return RANDOM.nextInt();
    }

}
