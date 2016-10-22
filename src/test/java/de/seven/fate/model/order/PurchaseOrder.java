package de.seven.fate.model.order;

import de.seven.fate.model.address.Address;
import de.seven.fate.model.person.Person;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

public class PurchaseOrder {

    @NotNull
    private Address address;

    @NotNull
    private Person person;

    @NotNull
    private List<PurchaseItem> orderItems;

    private Set<PurchaseItem> orderItemSet;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<PurchaseItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<PurchaseItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Set<PurchaseItem> getOrderItemSet() {
        return orderItemSet;
    }

    public void setOrderItemSet(Set<PurchaseItem> orderItemSet) {
        this.orderItemSet = orderItemSet;
    }
}
