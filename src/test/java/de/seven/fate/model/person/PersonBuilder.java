package de.seven.fate.model.person;

import de.seven.fate.model.address.AddressBuilder;
import de.seven.fate.model.builder.AbstractModelBuilder;


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
