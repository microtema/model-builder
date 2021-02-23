package de.microtema.model.builder.person;

import de.microtema.model.builder.address.AddressBuilder;
import de.microtema.model.builder.AbstractModelBuilder;


public class PersonBuilder extends AbstractModelBuilder<Person> {

    private final AddressBuilder addressBuilder;

    public PersonBuilder(AddressBuilder addressBuilder) {
        this.addressBuilder = addressBuilder;
    }

    @Override
    public Person min() {

        Person min = super.min();

        min.setAddresses(addressBuilder.list());

        return min;
    }
}
