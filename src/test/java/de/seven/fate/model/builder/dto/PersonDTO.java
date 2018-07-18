package de.seven.fate.model.builder.dto;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PersonDTO {

    @XmlElement(required = true)
    private Long id;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String email;

    @XmlElement(required = true)
    private String phoneNumber;

    @XmlElement(required = true)
    private Integer position;

    @XmlElement(required = true)
    private Date dob;

    @XmlElement
    private Date updateDate;

    @XmlElement
    private List<AddressDTO> addresses;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(id, personDTO.id) &&
                Objects.equals(name, personDTO.name) &&
                Objects.equals(email, personDTO.email) &&
                Objects.equals(phoneNumber, personDTO.phoneNumber) &&
                Objects.equals(position, personDTO.position) &&
                Objects.equals(dob, personDTO.dob) &&
                Objects.equals(updateDate, personDTO.updateDate) &&
                Objects.equals(addresses, personDTO.addresses);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, email, phoneNumber, position, dob, updateDate, addresses);
    }
}
