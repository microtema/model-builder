package de.microtema.model.builder.adapter.integer;

import de.microtema.model.builder.adapter.AbstractTypeRandomAdapter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

public class IntegerRandomAdapter extends AbstractTypeRandomAdapter<Integer> {

    public IntegerRandomAdapter(PositionPropertyRandomAdapter positionPropertyRandomAdapter,
                                ZipCodePropertyRandomAdapter zipCodePropertyRandomAdapter) {

        registerPropertyAdapter(positionPropertyRandomAdapter, zipCodePropertyRandomAdapter);
    }


    @Override
    protected Integer randomValueDefault(String propertyName) {

        return RANDOM.nextInt();
    }

    @Override
    public Integer fixValue(String propertyName) {

        return StringUtils.trimToEmpty(propertyName).length();
    }
}
