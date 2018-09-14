package de.seven.fate.model.builder.adapter.decimal;

import de.seven.fate.model.builder.adapter.AbstractTypeRandomAdapter;

import java.math.BigDecimal;

public class BigDecimalRandomAdapter extends AbstractTypeRandomAdapter<BigDecimal> {

    @Override
    protected BigDecimal randomValueDefault(String propertyName) {

        return BigDecimal.valueOf(RANDOM.nextLong(), 2);
    }

}
