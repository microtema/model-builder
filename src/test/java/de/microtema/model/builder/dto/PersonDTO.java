package de.microtema.model.builder.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
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
