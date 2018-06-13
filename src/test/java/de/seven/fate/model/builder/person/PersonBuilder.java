package de.seven.fate.model.builder.person;

import de.seven.fate.model.builder.AbstractModelBuilder;
import de.seven.fate.model.builder.address.AddressBuilder;


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
