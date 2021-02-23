package de.microtema.model.builder.adapter.decimal;

import de.microtema.model.builder.adapter.AbstractTypeRandomAdapter;

import java.math.BigDecimal;

public class BigDecimalRandomAdapter extends AbstractTypeRandomAdapter<BigDecimal> {

    @Override
    protected BigDecimal randomValueDefault(String propertyName) {

        return BigDecimal.valueOf(RANDOM.nextLong(), 2);
    }

    @Override
    public BigDecimal fixValue(String propertyName) {

        return BigDecimal.valueOf(propertyName.length());
    }
}
