package de.microtema.model.builder.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class PersonDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private Integer position;

    @NotNull
    private Date dob;

    @NotNull
    private Date updateDate;

    @NotNull
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
