package de.microtema.model.builder.app;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class App {

    @NotNull
    private String name;

    @NotNull
    private App parent;

    @NotNull
    private List<App> apps;

    @NotNull
    private List<App> children;
}
