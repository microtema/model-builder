package de.seven.fate.model.builder.app;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import java.time.LocalDateTime;
import java.util.List;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }

    public List<App> getSets() {
        return sets;
    }

    public void setSets(List<App> sets) {
        this.sets = sets;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
