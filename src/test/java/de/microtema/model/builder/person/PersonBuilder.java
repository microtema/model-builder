package de.microtema.model.builder.person;

import de.microtema.model.builder.ModelBuilder;
import de.microtema.model.builder.address.AddressBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonBuilder implements ModelBuilder<Person> {

    private final AddressBuilder addressBuilder;

    @Override
    public Person min() {

        Person min = ModelBuilder.super.min();

        min.setAddresses(addressBuilder.list());

        return min;
    }
}
