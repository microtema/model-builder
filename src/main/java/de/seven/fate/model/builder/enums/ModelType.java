package de.seven.fate.model.builder.enums;

public enum ModelType {

    /**
     * init only required fields
     */
    MIN,

    /**
     * init all fields on top level
     */
    MAX,

    /**
     * init as Min or Max
     */
    MIX,

    /**
     * init fields with Fix values
     */
    FIX,

    /**
     * init from resource
     */
    SOURCE
}
