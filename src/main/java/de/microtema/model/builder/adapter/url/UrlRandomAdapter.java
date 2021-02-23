package de.microtema.model.builder.adapter.url;

import de.microtema.model.builder.adapter.string.LinkPropertyRandomAdapter;
import de.microtema.model.builder.adapter.AbstractTypeRandomAdapter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.net.MalformedURLException;
import java.net.URL;

@RequiredArgsConstructor
public class UrlRandomAdapter extends AbstractTypeRandomAdapter<URL> {

    private final LinkPropertyRandomAdapter linkPropertyRandomAdapter;

    @Override
    @SneakyThrows
    protected URL randomValueDefault(String propertyName) {

        return new URL(linkPropertyRandomAdapter.randomValue());
    }


    @Override
    @SneakyThrows
    public URL fixValue(String propertyName) {

        return new URL(linkPropertyRandomAdapter.fixValue(propertyName));
    }
}
