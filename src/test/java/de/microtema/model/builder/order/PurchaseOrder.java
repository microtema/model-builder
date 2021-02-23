package de.microtema.model.builder.order;

import de.microtema.model.builder.address.Address;
import de.microtema.model.builder.person.Person;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
public class PurchaseOrder {

    @NotNull
    private Address address;

    @NotNull
    private Person person;

    @NotNull
    private List<PurchaseItem> orderItems;

    private Set<PurchaseItem> orderItemSet;
}
