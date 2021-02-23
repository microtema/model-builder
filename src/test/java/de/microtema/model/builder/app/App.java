package de.microtema.model.builder.app;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class App {

    @NotNull
    @XmlAttribute
    private String name;

    private String title;

    @NotNull
    private List<App> apps;

    @NotNull
    private List<App> sets;

    @NotNull
    private LocalDateTime localDateTime;
}
