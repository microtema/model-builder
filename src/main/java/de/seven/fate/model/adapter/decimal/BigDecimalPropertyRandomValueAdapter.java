package de.seven.fate.model.adapter.decimal;

import de.seven.fate.model.adapter.AbstractPropertyRandomValueAdapter;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by Mario on 24.03.2016.
 */
public class BigDecimalPropertyRandomValueAdapter extends AbstractPropertyRandomValueAdapter<BigDecimal> {


    @Override
    protected BigDecimal randomValueImpl(String propertyName, Class<?> objectType) {

        return BigDecimal.valueOf(new Random().nextLong(), 2);
    }

}
