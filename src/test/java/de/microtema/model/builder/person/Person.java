package de.microtema.model.builder.person;


import de.microtema.model.builder.address.Address;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Person {

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

    private Date updateDate;

    private BigDecimal amount;

    private Float phoneBill;

    private List<Address> addresses;
}
