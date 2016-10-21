package de.seven.fate.model.app;

import java.util.List;

public class App {

    private String name;

    private List<App> apps;
    private List<App> sets;

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
}
