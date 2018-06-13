package de.seven.fate.model.builder.adapter.url;

import de.seven.fate.model.builder.adapter.AbstractTypeRandomAdapter;
import de.seven.fate.model.builder.adapter.string.LinkPropertyRandomAdapter;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mario on 06.04.2016.
 */
public class UrlRandomAdapter extends AbstractTypeRandomAdapter<URL> {


    @Override
    protected URL randomValueDefault(String propertyName) {
        try {
            return new URL(LinkPropertyRandomAdapter.randomLink());
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
