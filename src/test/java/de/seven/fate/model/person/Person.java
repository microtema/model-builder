package de.seven.fate.model.person;


import de.seven.fate.model.address.Address;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Mario on 24.03.2016.
 */
public class Person {

    private Long id;

    private String name;

    private String email;
    private String phoneNumber;
    private Integer position;

    private Date dob;

    private Date updateDate;

    private BigDecimal amount;

    private Float phoneBill;

    private List<Address> addresses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Float getPhoneBill() {
        return phoneBill;
    }

    public void setPhoneBill(Float phoneBill) {
        this.phoneBill = phoneBill;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
