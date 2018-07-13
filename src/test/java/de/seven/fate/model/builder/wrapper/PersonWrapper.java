package de.seven.fate.model.builder.wrapper;

import de.seven.fate.model.builder.address.Address;
import de.seven.fate.model.builder.person.Person;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PersonWrapper {

    private Person person = new Person();

    public Long getId() {
        return person.getId();
    }

    public void setId(Long id) {
        person.setId(id);
    }

    public String getName() {
        return person.getName();
    }

    public void setName(String name) {
        person.setName(name);
    }

    public String getEmail() {
        return person.getEmail();
    }

    public void setEmail(String email) {
        person.setEmail(email);
    }

    public String getPhoneNumber() {
        return person.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        person.setPhoneNumber(phoneNumber);
    }

    public Integer getPosition() {
        return person.getPosition();
    }

    public void setPosition(Integer position) {
        person.setPosition(position);
    }

    public Date getDob() {
        return person.getDob();
    }

    public void setDob(Date dob) {
        person.setDob(dob);
    }

    public Date getUpdateDate() {
        return person.getUpdateDate();
    }

    public void setUpdateDate(Date updateDate) {
        person.setUpdateDate(updateDate);
    }

    public BigDecimal getAmount() {
        return person.getAmount();
    }

    public void setAmount(BigDecimal amount) {
        person.setAmount(amount);
    }

    public Float getPhoneBill() {
        return person.getPhoneBill();
    }

    public void setPhoneBill(Float phoneBill) {
        person.setPhoneBill(phoneBill);
    }

    public List<Address> getAddresses() {
        return person.getAddresses();
    }

    public void setAddresses(List<Address> addresses) {
        person.setAddresses(addresses);
    }
}
